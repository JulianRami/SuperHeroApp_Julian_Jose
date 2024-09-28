package com.example.superheroapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroapp.data.generateSuperheroes
import com.example.superheroapp.ui.screens.friends.uiState.FriendUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FriendViewModel: ViewModel() {
    private val _friend = MutableStateFlow(FriendUiState());
    val friend = _friend.asStateFlow();
    val dataHelper = generateSuperheroes();

    fun getHero(id: Int){
        viewModelScope.launch {
            dataHelper.find { it.id == id }?.let {
                val hero = _friend.value.copy(
                    superhero = it,
                    isFriendLoading = false
                )
                _friend.value = hero
            }
        }
    }
}