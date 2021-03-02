package com.aria.document

import com.aria.dto.NewsDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("news")
class NewsDocument(
    @Id
    val id: String? = null,
    val feedId: String,
    val title: String,
    val link: String,
    val description: String,
    val date: Long
) {
    fun toDto() = NewsDto(id, feedId, title, link, description, date)
}