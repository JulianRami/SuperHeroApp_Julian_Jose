package com.example.superheroapp.ui.screens.friends.uiState

import com.example.superheroapp.data.models.Superhero

data class FriendUiState(
    val superhero: Superhero? = null,
    val isFriendLoading: Boolean = true,
)
