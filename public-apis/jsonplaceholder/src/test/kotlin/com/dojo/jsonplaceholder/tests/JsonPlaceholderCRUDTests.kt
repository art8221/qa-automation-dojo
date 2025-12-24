package com.dojo.jsonplaceholder.tests

import io.restassured.RestAssured.given
import io.restassured.http.ContentType.JSON
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JsonPlaceholderCRUDTests {
    
    @Test
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
        
        assertThat(response.jsonPath().getInt("id")).isGreaterThan(0)
        assertThat(response.jsonPath().getString("title")).isEqualTo("Test Post")
        println("✅ POST test passed! Created post with ID: ${response.jsonPath().getInt("id")}")
    }
}
