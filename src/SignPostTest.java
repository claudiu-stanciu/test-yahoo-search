import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

/**
 * Sample code to use Yahoo Search BOSS
 *
 * Please include the following libraries 1. Apache Log4j 2. oAuth Signpost
 *
 * @author xyz
 */
public class SignPostTest {

	private static final Logger log = Logger.getLogger(SignPostTest.class);

	protected static String yahooServer = "https://yboss.yahooapis.com/ysearch/news";

	// Please provide your consumer key here
	private static String consumer_key = "dj0yJmk9SDRNckwyejBLdHhUJmQ9WVdrOWVsaE9jRkZ4TmpJbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD05OQ--";

	// Please provide your consumer secret here
	private static String consumer_secret = "3c969863a1a42dc141d0771cf206343fdf25c0fb";

	/** The HTTPS request object used for the connection */
	private static StHttpRequest httpsRequest = new StHttpRequest();

	/** Encode Format */
	private static final String ENCODE_FORMAT = "UTF-8";

	/** Call Type */
	//private static final String callType = "news";

	private static final int HTTP_STATUS_OK = 200;

	/**
	 *
	 * @return
	 */
	public int returnHttpData() throws UnsupportedEncodingException, Exception {

		if (this.isConsumerKeyExists() && this.isConsumerSecretExists()) {

			// Start with call Type
			String params = "";

			// Add query
			params = params.concat("?q=");

			// Encode Query string before concatenating
			params = params.concat(URLEncoder.encode(this.getSearchString(), "UTF-8"));

			// Create final URL
			String url = yahooServer + params;

			// Create oAuth Consumer
			OAuthConsumer consumer = new DefaultOAuthConsumer(consumer_key, consumer_secret);

			// Set the HTTPS request correctly
			httpsRequest.setOAuthConsumer(consumer);

			try {
				log.info("Sending get request to " + URLDecoder.decode(url, ENCODE_FORMAT));
				int responseCode = httpsRequest.sendGetRequest(url);

				// Send the request
				if (responseCode == HTTP_STATUS_OK) {
					log.info("Response ");
				} else {
					log.error("Error in response due to status code = " + responseCode);
				}
				log.info(httpsRequest.getResponseBody());

			} catch (UnsupportedEncodingException e) {
				log.error("Encoding/Decording error");
			} catch (IOException e) {
				log.error("Error with HTTP IO", e);
			} catch (Exception e) {
				log.error(httpsRequest.getResponseBody(), e);
				return 0;
			}

		} else {
			log.error("Key/Secret does not exist");
		}
		return 1;
	}

	private String getSearchString() {
		return "Yahoo";
	}

	private boolean isConsumerKeyExists() {
		if (consumer_key.isEmpty()) {
			log.error("Consumer Key is missing. Please provide the key");
			return false;
		}
		return true;
	}

	private boolean isConsumerSecretExists() {
		if (consumer_secret.isEmpty()) {
			log.error("Consumer Secret is missing. Please provide the key");
			return false;
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BasicConfigurator.configure();

		try {

			SignPostTest signPostTest = new SignPostTest();

			signPostTest.returnHttpData();

		} catch (Exception e) {
			log.info("Error", e);
		}
	}

}