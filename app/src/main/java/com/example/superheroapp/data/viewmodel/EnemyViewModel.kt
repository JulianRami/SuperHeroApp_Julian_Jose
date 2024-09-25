package com.example.superheroapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroapp.data.generateSuperheroes
import com.example.superheroapp.ui.screens.enemies.uiState.EnemyUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class EnemyViewModel: ViewModel() {
    private val _enemy = MutableStateFlow(EnemyUiState());
    val enemy = _enemy.asStateFlow();
    val dataHelper = generateSuperheroes();

    fun getHero(id: Int){
        viewModelScope.launch {
            dataHelper.find { it.id == id }?.let {
                val hero = _enemy.value.copy(
                    superhero = it,
                    isEnemyLoading = false
                )
                _enemy.value = hero
            }
        }
    }


}