# Yahoo news developer test

The project aims to run news search squeries over the Yahoo BOSS api, but it's [been discontinued](https://developer.yahoo.com/boss/search/#get-started)

## Build
```
mvn clean install
```

## Run
```
mvn exec:java
```

It will try a news search query for "Yahoo" `https://yboss.yahooapis.com/ysearch/news?q=Yahoo`, and you will get a:

```
...
1777 [SignPostTest.main()] ERROR SignPostTest  - Error in response due to status code = 403
1778 [SignPostTest.main()] INFO SignPostTest  - {"error":{"lang":"en-US","description":"Valid AppID but requires registration."}}
```
