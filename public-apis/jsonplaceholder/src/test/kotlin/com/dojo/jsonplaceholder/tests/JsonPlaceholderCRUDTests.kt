package com.dojo.jsonplaceholder.tests

import com.dojo.jsonplaceholder.annotations.ParallelApiTest
import io.qameta.allure.Description
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

@ParallelApiTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
class JsonPlaceholderCRUDTests {

    @Test
    @Description("POST: Создание нового поста должно вернуть 201 и ID")
    fun `POST create new post should return 201 with id`() {
        val requestBody = """
        {
            "title": "Test Post",
            "body": "This is a test post created by automation",
            "userId": 1
        }
        """.trimIndent()

        val response = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .`when`()
            .post("/posts")
            .then()
            .statusCode(201)
            .extract()
            .response()

        val expectedId = 101
        val expectedTitle = "Test Post"

        assertEquals(expectedId, response.jsonPath().getInt("id"),
            "Id не соответствует ожидаемому"
        )

        assertEquals(expectedTitle, response.jsonPath().getString("title"),
            "Заголовок не соответствует ожидаемому"
        )
    }

    @Test
    @Description("GET: Получение существующего поста должно вернуть 200")
    fun `GET existing post should return 200`() {
        RestAssured.given()
            .`when`()
            .get("/posts/1")
            .then()
            .statusCode(200)
    }

    @Test
    @Description("PUT: Обновление поста должно вернуть 200")
    fun `PUT update post should return 200`() {
        val requestBody = """
        {
            "title": "Updated Post",
            "body": "Updated content",
            "userId": 1,
            "id": 1
        }
        """.trimIndent()

        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .`when`()
            .put("/posts/1")
            .then()
            .statusCode(200)
    }

    @Test
    @Description("DELETE: Удаление поста должно вернуть 200")
    fun `DELETE post should return 200`() {
        RestAssured.given()
            .`when`()
            .delete("/posts/1")
            .then()
            .statusCode(200)
    }
}