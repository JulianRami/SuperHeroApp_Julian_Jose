package com.example.superheroapp.data.utils

fun String.getIdFromUrl(): Int? =
    this.split("/").lastOrNull()?.toIntOrNull()
