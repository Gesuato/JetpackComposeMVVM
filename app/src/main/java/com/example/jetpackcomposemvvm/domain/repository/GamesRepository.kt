package com.example.jetpackcomposemvvm.domain.repository

import android.os.RemoteException
import com.example.jetpackcomposemvvm.remote.api.GameApi
import com.example.jetpackcomposemvvm.paging.Paging
import com.example.jetpackcomposemvvm.domain.entity.Game
import com.example.jetpackcomposemvvm.remote.model.toEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesRepository @Inject constructor(private val gameApi: GameApi) {

    suspend fun getPaginatedGames(page: Int, pageSize: Int): Paging<String, Game>? {
        return try {
            gameApi.getAllGames(page, pageSize).entity { it.toEntity() }
        } catch (e: RemoteException) {
            null
        }
    }
}
