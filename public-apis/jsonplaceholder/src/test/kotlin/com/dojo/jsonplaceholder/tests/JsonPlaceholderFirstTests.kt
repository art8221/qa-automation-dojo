package com.dojo.jsonplaceholder.tests

import io.restassured.RestAssured.given
import io.restassured.http.ContentType.JSON
import jdk.jfr.Description
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

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

        val expectedId = 1
        val expectedTitle = "Test Get"

        Assertions.assertEquals(expectedId, post["id"], "Не соответствует!!!")
        Assertions.assertEquals(expectedTitle, post["title"], "Не соответствует2!!!")
    }
}
