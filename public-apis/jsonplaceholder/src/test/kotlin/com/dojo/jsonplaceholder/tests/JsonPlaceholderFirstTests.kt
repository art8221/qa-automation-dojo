package com.dojo.jsonplaceholder.tests

import io.restassured.RestAssured.given
import io.restassured.http.ContentType.JSON
import jdk.jfr.Description
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertAll

class JsonPlaceholderFirstTests {

    @Test
    @Description("POST: Возвращение корректной информации")
    fun `GET post by id should return correct data`() {
        val post = given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .contentType(JSON)
            .`when`()
            .get("/posts/1")
            .then()
            .statusCode(200)
            .extract()
            .`as`(Map::class.java)

        val expectedId = 12

        val expectedTitle = "Test Get"

        assertAll(
            "Проверки данных поста",
            { assertEquals(expectedId, post["id"], "ID не соответствует ожидаемому, " +
                    "ожидается - '$expectedId'," +
                    "фактически - '${post["id"]}'") },
            { assertEquals(expectedTitle, post["title"], "Заголовок не соответствует ожидаемому, " +
                    "ожидается - '$expectedTitle'," +
                    "фактически - '${post["title"]}'") }
        )
    }
}
