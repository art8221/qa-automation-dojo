package com.dojo.jsonplaceholder.config

import io.qameta.allure.Allure
import io.restassured.filter.Filter
import io.restassured.filter.FilterContext
import io.restassured.response.Response
import io.restassured.specification.FilterableRequestSpecification
import io.restassured.specification.FilterableResponseSpecification

class TestLoggingFilter : Filter {

    companion object {
        private val ENABLE_CONSOLE_LOGS = System.getenv("ENABLE_REST_LOGGING")?.toBoolean() ?: true
    }

    override fun filter(
        requestSpec: FilterableRequestSpecification,
        responseSpec: FilterableResponseSpecification,
        ctx: FilterContext
    ): Response {

        val testName = getCurrentTestName()
        val threadName = Thread.currentThread().name

        if (ENABLE_CONSOLE_LOGS) {
            println("\n[START TEST] $testName [Thread: $threadName]")
            println("${requestSpec.method} ${requestSpec.uri}")
        }

        val response = ctx.next(requestSpec, responseSpec)

        // Allure логирование
        Allure.addAttachment(
            "Request - $testName",
            "text/plain",
            "Method: ${requestSpec.method}\nURL: ${requestSpec.uri}"
        )

        Allure.addAttachment(
            "Response - $testName",
            "text/plain",
            "Status: ${response.statusCode}\nTime: ${response.time}ms\nBody: ${response.body.asString().take(500)}"
        )

        if (ENABLE_CONSOLE_LOGS) {
            println("[END TEST] $testName - Status: ${response.statusCode}, Time: ${response.time}ms\n")
        }

        return response
    }

    private fun getCurrentTestName(): String {
        return try {
            // Получаем имя теста из стека вызовов
            Thread.currentThread().stackTrace
                .asSequence()
                .dropWhile {
                    // Пропускаем служебные методы
                    it.className.startsWith("io.restassured") ||
                            it.className.startsWith("java.") ||
                            it.className.startsWith("kotlin.") ||
                            it.className.startsWith("jdk.internal.") ||
                            it.className.startsWith("sun.")
                }
                .firstOrNull {
                    // Ищем методы тестов
                    it.className.contains("Test") &&
                            (it.methodName.startsWith("`") ||
                                    it.methodName.startsWith("test") ||
                                    it.methodName.contains("should") ||
                                    it.methodName.contains("must"))
                }
                ?.let { trace ->
                    // Форматируем имя теста
                    val methodName = trace.methodName
                    when {
                        methodName.startsWith("`") && methodName.endsWith("`") ->
                            methodName.removeSurrounding("`")
                        else -> methodName
                    }
                } ?: "UnknownTest"
        } catch (e: Exception) {
            "ErrorGettingTestName: ${e.message}"
        }
    }
}