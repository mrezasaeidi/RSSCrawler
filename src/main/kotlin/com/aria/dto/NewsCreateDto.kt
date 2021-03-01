package com.aria.dto

import com.aria.document.NewsDocument

data class NewsCreateDto(
    val feedId: Long,
    val title: String,
    val link: String,
    val description: String,
    val date: Long
)

fun NewsCreateDto.to() = NewsDocument(null, feedId, title, link, description, date)