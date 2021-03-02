package com.aria.service

import com.aria.document.FeedDocument
import com.aria.dto.FeedDto
import com.aria.repository.FeedRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FeedService @Autowired constructor(private val feedRepository: FeedRepository) {

    fun add(feed: FeedDocument): FeedDocument {
        return feedRepository.save(feed)
    }

    fun addAll(feeds: Iterable<FeedDocument>): List<FeedDocument> {
        return feedRepository.saveAll(feeds)
    }

    fun getAll(): List<FeedDto> {
        return feedRepository.findAll().map { it.toDto() }
    }

    fun deleteAll() {
        feedRepository.deleteAll()
    }
}