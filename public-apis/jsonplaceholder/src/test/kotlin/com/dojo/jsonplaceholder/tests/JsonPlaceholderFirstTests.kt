package com.dojo.jsonplaceholder.tests

import io.restassured.RestAssured.given
import io.restassured.http.ContentType.JSON
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JsonPlaceholderFirstTests {
    
    @Test
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
        
        assertThat(post["id"]).isEqualTo(1)
        assertThat(post["title"]).isNotNull()
        println("✅ First test passed! Ура!!! Post title: ${post["title"]}")
    }
}
