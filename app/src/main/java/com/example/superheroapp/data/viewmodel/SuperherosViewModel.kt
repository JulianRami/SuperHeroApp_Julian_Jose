package com.example.superheroapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroapp.data.di.DataHelper
import com.example.superheroapp.ui.screens.superheros.uiState.SuperheroUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SuperherosViewModel @Inject constructor(
    private val dataHelper: DataHelper): ViewModel() {

    private val _uiState = MutableStateFlow(SuperheroUiState())
    val uiState: StateFlow<SuperheroUiState> = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            getSuperheros()
        }
    }

    private suspend fun getSuperheros() {
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            superheros = dataHelper.generateSuperheroes()
        )
    }
}
