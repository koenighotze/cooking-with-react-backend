package org.koenighotze.cooking.config

import jakarta.transaction.Transactional
import mu.KotlinLogging
import org.koenighotze.cooking.recipes.Ingredient
import org.koenighotze.cooking.recipes.IngredientRepository
import org.koenighotze.cooking.recipes.Recipe
import org.koenighotze.cooking.recipes.RecipeRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.UUID.randomUUID

private val logger = KotlinLogging.logger {}

@Configuration
class DbInitializer {
    companion object Samples {
        val recipes = listOf(
            Recipe(
                id = "1",
                title = "Seelenwärmer - Gemüseeintopf",
                description = "Buttern wie bei Muttern",
                instructions = "1. Die Zwiebeln abziehen, in feine Ringe schneiden. Die Kartoffeln schälen, waschen. Die Karotten waschen, Kohlrabi schälen und alles würfeln.\n" +
                    "\n" +
                    "2. Die Butter oder Margarine in einem großen Topf zerlassen und die Zwiebelringe darin glasig dünsten. Das übrige Gemüse dazu geben, ebenfalls andünsten. Dann die heiße Brühe angießen. Den Eintopf mit Salz (sehr gut geeignet ist auch Selleriesalz) und Pfeffer würzen und etwa 30 Minuten köcheln lassen.\n" +
                    "\n" +
                    "3. Zum Schluss die Sahne unterrühren, nicht mehr kochen lassen.",
                servings = 4,
                totalTimeMinutes = 30,
                prepTimeMinutes = 30,
                ingredients = listOf(
                    Ingredient(id = randomUUID().toString(), name = "Rote Zwiebel", amount = "2"),
                    Ingredient(id = randomUUID().toString(), name = "Karotte", amount = "600g"),
                    Ingredient(id = randomUUID().toString(), name = "Kohlrabi", amount = "2")
                )
            ),
            Recipe(
                id = "2",
                title = "Schlemmertopf mit Hackfleisch",
                description = "Was für die kalten Tage",
                instructions = "1. Zwiebel und Knoblauch kleinschneiden. Paprikaschoten, Möhren und Kartoffeln in kleine, gleich große Würfel schneiden.\n" +
                    "\n" +
                    "2. Öl in einem Suppentopf erhitzen, Hackfleisch darin anbraten. Zwiebel und Knoblauch dazugeben, würzen, mit Tomatenmark verrühren und die Brühe angießen. Dann das gewürfelte Gemüse, die Bohnen, den Thymian und den Mais dazugeben. Mit Salz, Pfeffer und Paprikapulver würzen.Einmal umrühren, zugedeckt auf niedriger Stufe ca. 40 Min köcheln lassen.\n" +
                    "\n" +
                    "3. Zum Schluss den Schmelzkäse und die Sahne einrühren und nochmals abschmecken. Mit einem Häubchen aus Crème fraîche und mit Petersilie bestreut servieren. ",
                servings = 6,
                totalTimeMinutes = 30,
                prepTimeMinutes = 15,
                ingredients = listOf(
                    Ingredient(id = randomUUID().toString(), name = "Hackfleisch", amount = "500g"),
                    Ingredient(id = randomUUID().toString(), name = "Zwiebeln", amount = "2"),
                    Ingredient(id = randomUUID().toString(), name = "Paprika", amount = "2"),
                    Ingredient(id = randomUUID().toString(), name = "Mais", amount = "1/2 Dose")
                )
            ),
            Recipe(
                id = "3",
                title = "Würstchengulasch",
                description = "Richtig lecker für die Veganer",
                instructions = "1. Die Würstchen in ca. 0,5 cm dicke Scheiben schneiden. Zwiebel schälen, halbieren und ebenfalls in Scheiben schneiden. Den Speck würfeln. Paprika und Chili waschen, entkernen und würfeln, Tomaten waschen und würfeln. Knoblauch schälen und fein hacken.\n" +
                    "\n" +
                    "2. Öl in einer Pfanne erhitzen und die Speckwürfel darin glasig dünsten. Würstchen und Zwiebeln zufügen und alles braun braten. Das Tomatenmark zufügen und anrösten. Gemüse und Knoblauch zufügen und 3 - 5 min anschwitzen.\n" +
                    "\n" +
                    "3. Mit der Brühe und Crème fraîche ablöschen und aufkochen lassen. Nun Thymian, Rosmarin und Lorbeerblatt zufügen. Alles etwa 15 - 20 min bei schwacher Hitze dünsten, anschließend den Rosmarinzweig und das Lorbeerblatt entfernen.\n" +
                    "\n" +
                    "4. Paprikapulver (großzügig, ich nehme einen gehäuften TL) und Kreuzkümmel unterrühren und mit Salz, Pfeffer und einer Prise Zucker abschmecken. Noch etwas reduzieren lassen oder mit dem Saucenbinder binden. Die Schnittlauchröllchen unterheben und noch 5 Minuten ohne Hitze ziehen lassen.\n",
                servings = 3,
                totalTimeMinutes = 50,
                prepTimeMinutes = 20,
                ingredients = listOf(
                    Ingredient(id = randomUUID().toString(), name = "Wiener Würstchen", amount = "4"),
                    Ingredient(id = randomUUID().toString(), name = "Schinkenspeck", amount = "100g"),
                    Ingredient(id = randomUUID().toString(), name = "Zwiebel", amount = "3 große"),
                    Ingredient(id = randomUUID().toString(), name = "Knoblauch", amount = "4 Zehen"),
                    Ingredient(id = randomUUID().toString(), name = "Tomatenmark", amount = "2 EL"),
                    Ingredient(id = randomUUID().toString(), name = "Chilischote", amount = "1")

                )
            )
        )
    }

    @Bean
    @Transactional
    fun initDatabase(recipeRepository: RecipeRepository, ingredientRepository: IngredientRepository) = ApplicationRunner {
        if (recipeRepository.findAll().any()) {
            logger.info("Database contains recipes. Skipping initialization")
        } else {
            logger.info("Setting up recipes")
            recipes.forEach {
                it.ingredients.forEach { ingredient -> ingredientRepository.save(ingredient) }
                recipeRepository.save(it)
            }
        }
    }
}
