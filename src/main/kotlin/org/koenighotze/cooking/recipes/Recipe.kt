package org.koenighotze.cooking.recipes

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.OneToMany

@Entity
class Ingredient(
    @Id val id: String,
    val name: String,
    val amount: String
)

@Entity
class Recipe(
    @Id val id: String,
    val title: String,
    val description: String,
    @Lob val instructions: String,
    val servings: Int,
    val totalTimeMinutes: Int,
    val prepTimeMinutes: Int,
    @OneToMany val ingredients: List<Ingredient>
)
