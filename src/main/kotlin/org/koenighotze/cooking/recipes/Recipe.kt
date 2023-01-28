package org.koenighotze.cooking.recipes

import jakarta.persistence.CascadeType
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.FetchType.EAGER
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Lob
import jakarta.persistence.OneToMany

@Entity
class Ingredient(
    @Id val id: String,
    var name: String,
    var amount: String
)

@Entity
class Recipe(
    @Id val id: String,
    var title: String,
    var description: String,
    @Lob var instructions: String,
    var servings: Int,
    var totalTimeMinutes: Int,
    var prepTimeMinutes: Int,
    @OneToMany(cascade = [ ALL ], fetch = EAGER, orphanRemoval = true) @JoinColumn(name = "recipe_id")
    var ingredients: MutableList<Ingredient>
)
