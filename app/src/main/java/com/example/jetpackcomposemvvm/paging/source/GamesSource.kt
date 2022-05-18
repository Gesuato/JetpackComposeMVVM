package com.example.jetpackcomposemvvm.paging.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpackcomposemvvm.domain.repository.GamesRepository
import com.example.jetpackcomposemvvm.domain.entity.Game
import javax.inject.Inject

class GamesSource @Inject constructor(
    private val gamesRepository: GamesRepository
) : PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        val nextPage = params.key ?: 1
        val gamesResponse = gamesRepository.getPaginatedGames(nextPage, PAGE_SIZE)
        return if (gamesResponse?.data == null) {
            LoadResult.Error(Exception())
        } else {
            LoadResult.Page(
                data = gamesResponse.data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        }
    }
}

private const val PAGE_SIZE = 12