package com.example.superheroapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroapp.data.generateSuperheroes
import com.example.superheroapp.data.ui.screens.superheros.uiState.SuperheroUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SuperherosViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(SuperheroUiState())
    val uiState: StateFlow<SuperheroUiState> = _uiState.asStateFlow()
    val dataHelper = generateSuperheroes();

    init {
        viewModelScope.launch {
            getSuperheros()
        }
    }

    private suspend fun getSuperheros() {
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            superheros = dataHelper
        )
    }
}
