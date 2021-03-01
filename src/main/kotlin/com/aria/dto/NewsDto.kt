package com.aria.dto

data class NewsDto(
    val id: Long?,
    val feedId: Long,
    val title: String,
    val link: String,
    val description: String,
    val date: Long
)