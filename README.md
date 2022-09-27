# RssWebApplication
This Application provides two APIs which are used to retrieve news from news agencies using RSS protocol.
 ## Adding Feed
You can add Feed by using POST method and send a name and a link. this link must be owned by a news agency like yahoo, BBC, etc. (e.g: https://sports.yahoo.com/sc/rss.xml)

## Getting News
You can get retrieved news by sending a GET request. You can set limitations for the news count by sending a request param named "limit" and passing an integer value.
