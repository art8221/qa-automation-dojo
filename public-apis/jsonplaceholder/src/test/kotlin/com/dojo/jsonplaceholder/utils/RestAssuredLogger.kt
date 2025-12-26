package com.dojo.jsonplaceholder.utils

import org.slf4j.LoggerFactory

object RestAssuredLogger {

    private val log = LoggerFactory.getLogger(RestAssuredLogger::class.java)

    // ЗАКОММЕНТИРУЙТЕ ВЕСЬ БЛОК init:
    // init {
    //     val isLoggingEnabled = System.getenv("ENABLE_REST_LOGGING")?.toBoolean() ?: true
    //     // ... весь остальной код закомментируйте
    // }
}