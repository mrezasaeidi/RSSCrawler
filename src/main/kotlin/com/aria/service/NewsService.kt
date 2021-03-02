package com.aria.service

import com.aria.document.NewsDocument
import com.aria.dto.NewsDto
import com.aria.repository.NewsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NewsService @Autowired constructor(private val newsRepository: NewsRepository) {

    fun add(news: NewsDocument): NewsDocument {
        return newsRepository.save(news)
    }

    fun addAll(news: Iterable<NewsDocument>): List<NewsDocument> {
        return newsRepository.saveAll(news)
    }

    fun getAll(): List<NewsDto> {
        return newsRepository.findAll().map { it.toDto() }.sortedBy { it.date }
    }

    fun deleteAll() {
        newsRepository.deleteAll()
    }

}