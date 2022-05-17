package com.example.jetpackcomposemvvm.presentation.composables.home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.example.jetpackcomposemvvm.presentation.composables.commonui.LoadingSpinner
import com.example.jetpackcomposemvvm.presentation.composables.home.HomeViewModel
import com.example.jetpackcomposemvvm.usecase.model.Game

@ExperimentalFoundationApi
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    GameList(homeViewModel)
}

@ExperimentalFoundationApi
@Composable
fun GameList(homeViewModel: HomeViewModel) {
    val listState = rememberLazyListState()
    val lazyPagingItems = homeViewModel.getPaginatedGames().flow.collectAsLazyPagingItems()
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        state = listState, cells = GridCells.Fixed(count = 2), content = {
            items(lazyPagingItems.itemCount) { index ->
                lazyPagingItems[index]?.let {
                    GameItem(game = it)
                }
            }
            lazyPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingSpinner() }
                        item { LoadingSpinner() }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingSpinner() }
                        item { LoadingSpinner() }
                    }
                }
            }
        })
}

@Composable
fun GameItem(game: Game) {
    Card(
        elevation = 20.dp,
        backgroundColor = Color.Black,
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .height(250.dp)
            .fillMaxWidth()
            .clickable {}) {

        Column {
            Image(
                contentScale = ContentScale.Crop,
                painter = rememberImagePainter(
                    data = game.backgroundImage,
                    builder = { crossfade(true) }
                ),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = game.name,
                color = Color.White,
                maxLines = 2,
            )
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Bottom),
                    text = game.rating.toString(),
                    color = Color.Yellow
                )
                Icon(
                    modifier = Modifier.align(Alignment.Bottom),
                    imageVector = Icons.Filled.Star,
                    tint = Color.Yellow,
                    contentDescription = null
                )
            }
        }
    }
}