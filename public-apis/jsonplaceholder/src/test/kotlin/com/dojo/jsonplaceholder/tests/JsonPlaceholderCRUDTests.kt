import io.qameta.allure.Description
import io.restassured.RestAssured.given
import io.restassured.http.ContentType.JSON
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
            .filter(io.qameta.allure.restassured.AllureRestAssured())
            .`when`()
            .post("/posts")
            .then()
            .statusCode(201)
            .extract()
            .response()

        val expectedId = 101
        val expectedTitle = "Test Get"

        Assertions.assertEquals(
            response.jsonPath().getInt("id"),
            expectedId,
            "Id не соответствует ожидаемому, " +
                    "ожидаем - '$expectedId', " +
                    "фактически - '${response.jsonPath().getInt("id")}'"
        )

        Assertions.assertEquals(
            response.jsonPath().getString("title"),
            expectedTitle,
            "Заголовок не соответствует ожидаемому, " +
                    "ожидаем - '$expectedTitle', " +
                    "фактически - '${response.jsonPath().getString("title")}'"
        )
    }
}