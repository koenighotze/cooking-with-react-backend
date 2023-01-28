package org.koenighotze.cooking.recipes

import org.springframework.data.repository.CrudRepository

interface RecipeRepository : CrudRepository<Recipe, String>
