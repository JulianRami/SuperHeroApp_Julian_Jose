package com.example.superheroapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroapp.data.di.DataHelper
import com.example.superheroapp.ui.screens.enemies.uiState.EnemyUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnemyViewModel @Inject constructor(
    private val dataHelper: DataHelper) : ViewModel() {

    private val _enemy = MutableStateFlow(EnemyUiState());
    val enemy = _enemy.asStateFlow();


    fun getHero(id: Int){
        viewModelScope.launch {
            dataHelper.generateSuperheroes().find { it.id == id }?.let {
                val hero = _enemy.value.copy(
                    superhero = it,
                    isEnemyLoading = false
                )
                _enemy.value = hero
            }
        }
    }
}