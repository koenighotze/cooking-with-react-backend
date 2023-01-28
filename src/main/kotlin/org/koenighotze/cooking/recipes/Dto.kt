package org.koenighotze.cooking.recipes

data class AddNewIngredientRequest(val name: String, val amount: String)

data class AddNewRecipeRequest(
    val title: String,
    val description: String,
    val instructions: String,
    val servings: Int,
    val totalTimeMinutes: Int,
    val prepTimeMinutes: Int,
    val ingredients: List<AddNewIngredientRequest>
)

data class AddNewRecipeResponse(val id: String)

data class UpdateIngredientRequest(val name: String, val amount: String)

data class UpdateRecipeRequest(
    val title: String?,
    val description: String?,
    val instructions: String?,
    val servings: Int?,
    val totalTimeMinutes: Int?,
    val prepTimeMinutes: Int?,
    val ingredients: List<UpdateIngredientRequest>?
)

data class UpdateRecipeResponse(val id: String)
