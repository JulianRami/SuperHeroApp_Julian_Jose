package com.example.superheroapp.data

import com.example.superheroapp.R
import com.example.superheroapp.data.models.Enemy
import com.example.superheroapp.data.models.Location
import com.example.superheroapp.data.models.Power
import com.example.superheroapp.data.models.Superhero
import kotlin.random.Random

fun generateSuperheroes(): List<Superhero> {
    val superheroes = mutableListOf<Superhero>()
    val enemies = generateEnemies()
    val locations = generateLocations()
    val powers = generatePowers()

    // Dummy data for superheroes
    val superheroNames = listOf("Superman", "Batman", "Wonder Woman", "Flash", "Aquaman", "Spider-Man", "Iron Man", "Captain America", "Hulk", "Thor")
    val alterNames = listOf("Clark Kent", "Bruce Wayne", "Diana Prince", "Barry Allen", "Arthur Curry", "Peter Parker", "Tony Stark", "Steve Rogers", "Bruce Banner", "Thor Odinson")

    for (i in 0 until 20) {
        val superhero = Superhero(
            id = i,
            name = superheroNames[i % superheroNames.size],
            alterName = alterNames[i % alterNames.size],
            photo = R.drawable.default_superhero, // Placeholder drawable resource
            powers = powers.shuffled().take(Random.nextInt(1, 6)).map { it.id },
            friends = (0..Random.nextInt(0, 5)).map { Random.nextInt(0, 20) }.distinct().take(Random.nextInt(1, 6)),
            mainEnemy = enemies.random(),
            enemies = (0..Random.nextInt(0, 5)).map { Random.nextInt(0, enemies.size) }.distinct().take(Random.nextInt(1, 6)),
            locations = (0..Random.nextInt(0, 3)).map { Random.nextInt(0, locations.size) }.distinct().take(Random.nextInt(1, 4))
        )
        superheroes.add(superhero)
    }

    return superheroes
}

fun generateEnemies(): List<Enemy> {
    return listOf(
        Enemy(id = 1, name = "Lex Luthor", photo = R.drawable.default_enemy),
        Enemy(id = 2, name = "Joker", photo = R.drawable.default_enemy),
        Enemy(id = 3, name = "Cheetah", photo = R.drawable.default_enemy),
        Enemy(id = 4, name = "Reverse Flash", photo = R.drawable.default_enemy),
        Enemy(id = 5, name = "Black Manta", photo = R.drawable.default_enemy)
    )
}

fun generateLocations(): List<Location> {
    return listOf(
        Location(id = 1, name = "Metropolis"),
        Location(id = 2, name = "Gotham City"),
        Location(id = 3, name = "Themyscira"),
        Location(id = 4, name = "Star City"),
        Location(id = 5, name = "Atlantis")
    )
}

fun generatePowers(): List<Power> {
    return listOf(
        Power(id = 1, name = "Flight"),
        Power(id = 2, name = "Super Strength"),
        Power(id = 3, name = "Invisibility"),
        Power(id = 4, name = "Telepathy"),
        Power(id = 5, name = "Speed"),
        Power(id = 6, name = "Water Manipulation"),
        Power(id = 7, name = "Super Intelligence")
    )
}
