package com.example.superheroapp.data.di

import com.example.superheroapp.data.models.Superhero
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SuperHeroModule {
    @Provides
    @Singleton
    fun dataHelper(): DataHelper {
        return DataHelper()
    }
}