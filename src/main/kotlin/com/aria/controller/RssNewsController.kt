package com.aria.controller

import com.aria.annotation.ApiMapping
import com.aria.dto.FeedCreateDto
import com.aria.dto.FeedDto
import com.aria.dto.NewsDto
import com.aria.service.DataBaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@ApiMapping
class RssNewsController @Autowired constructor(private val databaseService: DataBaseService) {

    @PostMapping("feed")
    fun addFeed(@RequestBody feed: FeedCreateDto): ResponseEntity<FeedDto> {
        val addedFeed = databaseService.addFeed(feed)
        return ResponseEntity(
            addedFeed,
            HttpStatus.CREATED
        )
    }

    @GetMapping("news")
    fun getNews(@RequestParam(required = false) limit: Int? = null): ResponseEntity<List<NewsDto>> {
        return ResponseEntity(databaseService.getAllNews(limit), HttpStatus.OK)
    }
}