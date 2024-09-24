package com.example.superheroapp.data.models

import androidx.annotation.DrawableRes

data class Enemy(
    val id: Int,
    val name: String,
    @DrawableRes val photo: Int
)
