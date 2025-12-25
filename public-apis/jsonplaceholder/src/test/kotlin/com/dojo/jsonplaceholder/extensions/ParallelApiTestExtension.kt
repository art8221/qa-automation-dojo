package com.dojo.jsonplaceholder.extensions

import io.restassured.RestAssured
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.slf4j.LoggerFactory

class ParallelApiTestExtension  : BeforeAllCallback {

    private val log = LoggerFactory.getLogger(ParallelApiTestExtension::class.java)

    override fun beforeAll(context: ExtensionContext) {
        // Простая базовая настройка RestAssured
        RestAssured.baseURI = System.getProperty(
            "api.base.url",
            "https://jsonplaceholder.typicode.com"
        )

        log.info("✅ Tests configured for: ${context.displayName}")
        log.info("Base URL: ${RestAssured.baseURI}")
    }
}