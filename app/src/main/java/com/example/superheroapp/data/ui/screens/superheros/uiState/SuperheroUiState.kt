package com.example.superheroapp.data.ui.screens.superheros.uiState

import com.example.superheroapp.data.models.Superhero

data class SuperheroUiState(
    val superheros: List<Superhero>? = emptyList(),
    val isLoading: Boolean = true
)
