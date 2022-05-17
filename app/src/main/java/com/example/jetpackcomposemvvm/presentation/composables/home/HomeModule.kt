package com.example.jetpackcomposemvvm.presentation.composables.home

import com.example.jetpackcomposemvvm.paging.source.GamesSource
import com.example.jetpackcomposemvvm.repository.GamesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    fun providesGamesSource(
        gamesRepository: GamesRepository
    ) = GamesSource(gamesRepository = gamesRepository)
}