package com.dojo.jsonplaceholder.tests

import com.dojo.jsonplaceholder.annotations.ParallelApiTest
import io.qameta.allure.Description
import io.restassured.RestAssured
import io.restassured.http.ContentType.JSON
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@ParallelApiTest
class JsonPlaceholderFirstTests {

    @Test
    @Description("GET: Возвращение корректной информации")
    fun `GET post by id should return correct data`() {
        val post = RestAssured.given()
            .contentType(JSON)
            .get("/posts/1")
            .then()
            .statusCode(200)
            .extract()
            .`as`(Map::class.java)

        assertEquals(1, post["id"])
        assertEquals(
            "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            post["title"]
        )
    }
}