package com.aria.repository

import com.aria.document.FeedDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface FeedRepository : MongoRepository<FeedDocument, String>