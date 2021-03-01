package com.aria.dto

import com.aria.document.FeedDocument

data class FeedCreateDto(
    val name: String,
    val url: String
)

fun FeedCreateDto.to() = FeedDocument(null, name, url)