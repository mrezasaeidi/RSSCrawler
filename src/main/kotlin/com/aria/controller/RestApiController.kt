package com.aria.controller

import com.aria.annotation.ApiMapping
import com.aria.db.StaticRepository
import com.aria.entity.Feed
import com.aria.entity.News
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@ApiMapping
class RestApiController {

    @PostMapping("rest/feed")
    fun addFeed(@RequestBody feed: Feed): ResponseEntity<String> {
        val addedFeed = StaticRepository.addFeed(feed)
        return ResponseEntity("${addedFeed.name} has added with id ${addedFeed.id}", HttpStatus.CREATED)
    }

    @GetMapping("rest/news")
    fun getNews(@RequestParam(required = false) limit: Int?=null): ResponseEntity<List<News>> {
        return ResponseEntity(StaticRepository.getNews(limit), HttpStatus.OK)
    }
}