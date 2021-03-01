package com.aria.dto

import com.aria.document.NewsDocument

data class NewsCreateDto(
    val feedId: Long,
    val title: String,
    val link: String,
    val description: String,
    val date: Long
)

fun NewsCreateDto.to() = NewsDocument(
    feedId = feedId,
    title = title,
    link = link,
    description = description,
    date = date
)