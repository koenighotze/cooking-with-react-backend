package org.koenighotze.cooking.recipes

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/recipes")
class RecipeController(val recipeRepository: RecipeRepository) {
    data class Recipes(val recipes: List<Recipe>)

    @GetMapping
    fun getAllRecipes(): Recipes {
        return Recipes(recipes = recipeRepository.findAll().toList())
    }

    @GetMapping("/{id}")
    fun getRecipe(@PathVariable("id") id: String): Recipe {
        return recipeRepository.findByIdOrNull(id) ?: throw ResponseStatusException(NOT_FOUND)
    }
}
