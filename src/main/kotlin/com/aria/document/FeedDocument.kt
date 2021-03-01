package com.aria.document

import com.aria.dto.FeedDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("feed")
class FeedDocument(
    @Id
    var id: Long,
    val name: String,
    val url: String
) {
    fun toDto() = FeedDto(id, name, url)
}