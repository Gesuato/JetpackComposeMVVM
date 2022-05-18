package com.example.jetpackcomposemvvm.remote.api

import com.example.jetpackcomposemvvm.paging.PagingResult
import com.example.jetpackcomposemvvm.remote.model.GameApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApi {

    @GET(GAME_BASE)
    suspend fun getAllGames(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
    ): PagingResult<String, GameApiResult>

    companion object {
        private const val GAME_BASE = "/api/games"
    }
}