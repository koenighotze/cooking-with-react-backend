package org.koenighotze.cooking.recipes

import org.springframework.data.repository.CrudRepository

interface IngredientRepository : CrudRepository<Ingredient, String>
