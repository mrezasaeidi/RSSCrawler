package com.aria.dto

data class NewsDto(
    val id: String?,
    val feedId: String,
    val title: String,
    val link: String,
    val description: String,
    val date: Long
)