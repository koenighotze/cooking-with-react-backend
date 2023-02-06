package org.koenighotze.cooking.recipes

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_ATOM_XML
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest
class RecipeControllerTest(@Autowired val mockMvc: MockMvc) {
    @MockkBean
    lateinit var recipeRepository: RecipeRepository

    @MockkBean
    lateinit var ingredientRepository: IngredientRepository

    @Test
    fun `list recipes should return the list of recipes`() {
        every { recipeRepository.findAll() } returns listOf()
        mockMvc.get(urlTemplate = "/recipes") {
            accept = APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(APPLICATION_JSON) }
            content { json(jsonContent = "{}") }
        }
    }

    @Test
    fun `should return not accepted if accept type is not json`() {
        every { recipeRepository.findAll() } returns listOf()
        mockMvc.get(urlTemplate = "/recipes") {
            accept = APPLICATION_ATOM_XML
        }.andExpect {
            status { isNotAcceptable() }
        }
    }

    @Test
    fun `should return the recipe and their ingredients`() {
        every { recipeRepository.findAll() } returns listOf(Random.recipe())
        mockMvc.get(urlTemplate = "/recipes") {
            accept = APPLICATION_JSON
        }.andDo {
            print()
        }.andExpect {
            status { isOk() }
            content { contentType(APPLICATION_JSON) }
            content { json(jsonContent = "{}") }
        }
    }
}
