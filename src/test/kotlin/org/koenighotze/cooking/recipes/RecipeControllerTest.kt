package org.koenighotze.cooking.recipes

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.koenighotze.cooking.config.DbInit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ContextConfiguration(initializers = [DbInit::class])
class RecipeControllerTest(@Autowired val restTemplate: TestRestTemplate) {
    @Test
    fun `get all recipes returns the recipe list`() {
        val response = restTemplate.getForEntity("/recipes", GetAllRecipesResponse::class.java)

        assertThat(response.statusCode).isEqualTo(OK)
    }

    @Test
    fun `get single recipe returns the recipe`() {
        val response = restTemplate.getForEntity("/recipes/1", GetRecipeResponse::class.java)
        assertThat(response.statusCode).isEqualTo(OK)
        assertThat(response.body?.recipe?.id).isEqualTo("1")
    }

    @Test
    fun `get single recipe returns 404 if unknown`() {
        val response = restTemplate.getForEntity("/recipes/not_there", GetRecipeResponse::class.java)

        assertThat(response.statusCode).isEqualTo(NOT_FOUND)
    }
}
