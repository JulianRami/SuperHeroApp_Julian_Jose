package com.example.superheroapp.ui.screens.enemies.uiState

import com.example.superheroapp.data.models.Enemy
import com.example.superheroapp.data.models.Superhero

data class EnemyUiState(
    val superhero: Superhero? = null,
    val isEnemyLoading: Boolean = true,
)
