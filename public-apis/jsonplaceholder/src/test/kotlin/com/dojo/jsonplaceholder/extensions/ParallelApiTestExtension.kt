package com.dojo.jsonplaceholder.extensions

import com.dojo.jsonplaceholder.config.TestLoggingFilter
import io.restassured.RestAssured
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.slf4j.LoggerFactory

class ParallelApiTestExtension : BeforeEachCallback {

    private val log = LoggerFactory.getLogger(ParallelApiTestExtension::class.java)

    // Храним имя теста для каждого потока
    companion object {
        private val currentTestName = ThreadLocal<String>()

        fun getCurrentTestName(): String = currentTestName.get() ?: "UnknownTest"
        fun setCurrentTestName(name: String) = currentTestName.set(name)
    }

    override fun beforeEach(context: ExtensionContext) {
        // Сохраняем имя теста для этого потока
        setCurrentTestName(context.displayName)

        // Настраиваем RestAssured
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()

        // Добавляем фильтр
        RestAssured.filters(TestLoggingFilter())

        log.info("✅ Настроен RestAssured для теста: ${context.displayName}")
    }
}