package com.aria.service

import com.aria.dto.*
import com.aria.repository.FeedRepository
import com.aria.repository.NewsRepository
import com.aria.util.HttpUtil
import org.json.XML
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Service
class DataBaseService @Autowired constructor(
    private val newsRepository: NewsRepository,
    private val feedRepository: FeedRepository
) {
    fun addFeed(feed: FeedCreateDto): FeedDto {
        return feedRepository.save(feed.to()).toDto().apply {
            refreshNews(this)
        }
    }

    fun getAllFeeds(): List<FeedDto> {
        return feedRepository.findAll().map { it.toDto() }
    }

    fun getAllNews(limit: Int?): List<NewsDto> {
        val allNews = newsRepository.findAll().map { it.toDto() }.sortedBy { it.date }
        limit ?: return allNews
        return allNews.subList(0, if (limit < 0) 0 else limit)
    }

    private fun refreshNews(addedFeed: FeedDto? = null) {
        val news = addedFeed?.let {
            fetchNews(it)
        } ?: run {
            newsRepository.deleteAll()
            val newsList = ArrayList<NewsCreateDto>()
            getAllFeeds().forEach { feed ->
                newsList.addAll(fetchNews(feed))
            }
            newsList
        }
        newsRepository.saveAll(news.map { it.to() })
    }

    private fun fetchNews(feed: FeedDto): List<NewsCreateDto> {
        val newsList = ArrayList<NewsCreateDto>()
        try {
            val newsObs = XML.toJSONObject(HttpUtil.get(feed.url))
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
                newsList.add(
                    NewsCreateDto(
                        feed.id!!,
                        newsOb.optString("title"),
                        newsOb.optString("link"),
                        newsOb.optString("description"),
                        date
                    )
                )
            }
        } catch (e: Exception) {
        }
        return newsList
    }
}