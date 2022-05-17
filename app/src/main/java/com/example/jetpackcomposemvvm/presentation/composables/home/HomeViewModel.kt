package com.example.jetpackcomposemvvm.presentation.composables.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.jetpackcomposemvvm.paging.source.GamesSource
import com.example.jetpackcomposemvvm.usecase.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val gamesSource: GamesSource) : ViewModel() {

    fun getPaginatedGames(): Pager<Int, Game> {
        return Pager(PagingConfig(12)) { gamesSource }
    }
}