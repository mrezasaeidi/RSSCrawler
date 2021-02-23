package com.aria.db

import com.aria.entity.Feed
import com.aria.entity.News
import com.aria.util.HttpUtil
import org.json.XML
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object StaticRepository {
    private var feedId = 0
    private var newsId = 0
    private val feeds = ArrayList<Feed>()
    private val news = HashMap<Int, ArrayList<News>>()


    fun addFeed(feed: Feed): Feed {
        feed.id = ++feedId
        feeds.add(feed)
        return feed
    }

    fun getNews(limit: Int?): List<News> {
        fetchNews()
        val allNews = ArrayList<News>()
        news.values.forEach {
            allNews.addAll(it)
        }
        return if (limit == null) {
            allNews.sortedBy { it.date }
        } else {
            allNews.sortedBy { it.date }.subList(0, if (limit < 0) 0 else limit)
        }
    }

    private fun fetchNews() {
        feeds.forEach {
            if (!news.containsKey(it.id)) {
                val newsList = ArrayList<News>()
                try {
                    val newsObs = XML.toJSONObject(HttpUtil.get(it.url))
                    val newsArr = newsObs.optJSONObject("rss").optJSONObject("channel").optJSONArray("item")
                    for (i in 0 until newsArr.length()) {
                        val newsOb = newsArr.optJSONObject(i)
                        val date = try {
                            SimpleDateFormat().parse(newsOb.optString("pubDate")).time
                        } catch (ignore: Exception) {
                            try {
                                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(newsOb.optString("pubDate")).time
                            } catch (ignore: Exception) {
                                Date().time
                            }
                        }
                        val news = News(
                            ++newsId,
                            newsOb.optString("title"),
                            newsOb.optString("link"),
                            newsOb.optString("description"),
                            date
                        )
                        newsList.add(news)
                    }
                } catch (e: Exception) {
                }
                news[it.id] = newsList
            }
        }
    }
}