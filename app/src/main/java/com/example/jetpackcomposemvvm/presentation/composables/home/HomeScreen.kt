package com.example.jetpackcomposemvvm.presentation.composables.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.jetpackcomposemvvm.presentation.composables.commonui.LoadingSpinner

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val listState = rememberLazyListState()
    val lazyPagingItems = homeViewModel.getPaginatedGames().flow.collectAsLazyPagingItems()
    LazyColumn(state = listState) {
        itemsIndexed(lazyPagingItems) { _, game ->
            game?.name?.let { Text(text = it) }
        }

        lazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingSpinner() }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingSpinner() }
                }
            }
        }
    }
}