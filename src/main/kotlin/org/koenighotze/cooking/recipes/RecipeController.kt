package org.koenighotze.cooking.recipes

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.util.UriComponentsBuilder
import java.util.UUID.randomUUID

@RestController
@RequestMapping("/recipes")
@Transactional
class RecipeController(val recipeRepository: RecipeRepository, val ingredientRepository: IngredientRepository) {
    data class Recipes(val recipes: List<Recipe>)

    @GetMapping
    fun getAllRecipes(): Recipes {
        return Recipes(recipes = recipeRepository.findAll().toList())
    }

    @GetMapping("/{id}")
    fun getRecipe(@PathVariable("id") id: String): Recipe {
        return recipeRepository.findByIdOrNull(id) ?: throw ResponseStatusException(NOT_FOUND)
    }

    @PostMapping
    fun addNewRecipe(@RequestBody request: AddNewRecipeRequest): ResponseEntity<AddNewRecipeResponse> {
        val recipe = Recipe(
            id = randomUUID().toString(),
            title = request.title,
            description = request.description,
            servings = request.servings,
            instructions = request.instructions,
            totalTimeMinutes = request.totalTimeMinutes,
            prepTimeMinutes = request.prepTimeMinutes,
            ingredients = request.ingredients.map { Ingredient(id = randomUUID().toString(), name = it.name, amount = it.amount) }.toMutableList()
        )
        recipeRepository.save(recipe)

        val uriComponents = UriComponentsBuilder.newInstance()
            .path("/recipes/{id}")
            .buildAndExpand(recipe.id)
        return ResponseEntity.created(uriComponents.toUri()).body(AddNewRecipeResponse(id = recipe.id))
    }

    @DeleteMapping("/{id}")
    fun removeRecipe(@PathVariable("id") id: String) {
        val recipe = recipeRepository.findByIdOrNull(id) ?: throw ResponseStatusException(NOT_FOUND)

        recipeRepository.delete(recipe)
    }

    @PatchMapping("/{id}")
    fun updateRecipe(@PathVariable("id") id: String, @RequestBody update: UpdateRecipeRequest): ResponseEntity<UpdateRecipeResponse> {
        val recipe = recipeRepository.findByIdOrNull(id) ?: throw ResponseStatusException(NOT_FOUND)

        recipe.title = update.title ?: recipe.title
        recipe.description = update.description ?: recipe.description
        recipe.instructions = update.instructions ?: recipe.instructions
        recipe.prepTimeMinutes = update.prepTimeMinutes ?: recipe.prepTimeMinutes
        recipe.totalTimeMinutes = update.totalTimeMinutes ?: recipe.totalTimeMinutes
        recipe.servings = update.servings ?: recipe.servings

        update.ingredients?.let { updatedIngredients ->
            recipe.ingredients.clear()
            updatedIngredients.map { Ingredient(id = randomUUID().toString(), name = it.name, amount = it.amount) }.forEach { recipe.ingredients.add(it) }
        }

        return ResponseEntity.ok(UpdateRecipeResponse(id = recipe.id))
    }
}
