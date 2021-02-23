package com.aria

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class RssWebApplication

fun main() {
    SpringApplicationBuilder(RssWebApplication::class.java).run()
}