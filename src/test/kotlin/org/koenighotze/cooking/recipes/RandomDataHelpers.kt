package org.koenighotze.cooking.recipes

import java.util.UUID.randomUUID

object Random {
    private fun string() = randomUUID().toString()

    fun recipe(ingredients: List<Ingredient> = listOf(ingredient())): Recipe =
        Recipe(
            id = string(),
            title = string(),
            description = string(),
            instructions = string(),
            servings = 0,
            totalTimeMinutes = 0,
            prepTimeMinutes = 0,
            ingredients = ingredients.toMutableList()
        )

    fun ingredient() = Ingredient(
        id = string(),
        name = string(),
        amount = string()
    )
}
