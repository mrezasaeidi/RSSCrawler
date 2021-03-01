package com.aria.repository

import com.aria.document.NewsDocument
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsRepository : MongoRepository<NewsDocument, Long>