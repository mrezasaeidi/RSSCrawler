package com.aria.config.mongodb

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.client.internal.MongoClientImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory

@Configuration
class MongoDbConfiguration {

    companion object {
        private const val DB_NAME = "rss_news"
        private const val URI = "localhost:27017"
        private const val USERNAME = "admin"
        private const val PASSWORD = "admin"
    }

    @Bean
    @Primary
    fun mongoDbFactory() = SimpleMongoClientDatabaseFactory(
        MongoClientImpl(
            MongoClientSettings.builder()
                .credential(MongoCredential.createCredential(USERNAME, DB_NAME, PASSWORD.toCharArray()))
                .applyConnectionString(ConnectionString(URI))
                .build(),
            null
        ),
        DB_NAME
    )

    @Bean
    @Primary
    fun mongoTemplate() = MongoTemplate(mongoDbFactory())
}