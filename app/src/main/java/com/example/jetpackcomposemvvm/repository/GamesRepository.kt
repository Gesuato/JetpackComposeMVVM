package com.example.jetpackcomposemvvm.repository

import android.os.RemoteException
import com.example.jetpackcomposemvvm.api.GameApi
import com.example.jetpackcomposemvvm.paging.Paging
import com.example.jetpackcomposemvvm.repository.model.toEntity
import com.example.jetpackcomposemvvm.usecase.model.Game
import javax.inject.Inject

class GamesRepository @Inject constructor(private val gameApi: GameApi) {

    suspend fun getPaginatedGames(page: Int, pageSize: Int): Paging<String, Game>? {
        return try {
            gameApi.getAllGames(page, pageSize).entity { it.toEntity() }
        } catch (e: RemoteException) {
            null
        }
    }
}
