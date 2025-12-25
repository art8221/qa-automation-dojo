package com.dojo.jsonplaceholder.tests


import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType.JSON
import jdk.jfr.Description
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

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

        val response = given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .contentType(JSON)
            .body(requestBody)
            .`when`()
            .post("/posts")
            .then()
            .statusCode(201)
            .extract()
            .response()

        val expectedId = 101
        val expectedTitle = "Test Get"

        Assertions.assertEquals(response.jsonPath().getInt("id"), expectedId, "id не равен $expectedId")

        Assertions.assertEquals(response.jsonPath().getString("title"), expectedTitle, "заголовок не равен $expectedTitle")


    }
}
