package com.example.superheroapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroapp.data.di.DataHelper
import com.example.superheroapp.ui.screens.friends.uiState.FriendUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor(
    private val dataHelper: DataHelper) : ViewModel() {
    private val _friend = MutableStateFlow(FriendUiState());
    val friend = _friend.asStateFlow();

    fun getHero(id: Int){
        viewModelScope.launch {
            dataHelper.generateSuperheroes().find { it.id == id }?.let {
                val hero = _friend.value.copy(
                    superhero = it,
                    isFriendLoading = false
                )
                _friend.value = hero
            }
        }
    }
}