package com.example.jetpackcomposemvvm.presentation.composables

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpackcomposemvvm.presentation.composables.Destinations.GameDetails
import com.example.jetpackcomposemvvm.presentation.composables.Destinations.GameDetailsArgs.GameId
import com.example.jetpackcomposemvvm.presentation.composables.Destinations.Home
import com.example.jetpackcomposemvvm.presentation.composables.details.GameDetailsScreen
import com.example.jetpackcomposemvvm.presentation.composables.home.HomeViewModel
import com.example.jetpackcomposemvvm.presentation.composables.home.ui.HomeScreen

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun ScreenPicker(homeViewModel: HomeViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }
    val homeLazyPagingItems = homeViewModel.pager.collectAsLazyPagingItems()
    NavHost(navController = navController, startDestination = Home) {
        composable(Home) {
            HomeScreen(
                lazyPagingItems = homeLazyPagingItems,
                openGameDetails = actions.openGameDetails
            )
        }
        composable(
            route = "$GameDetails/{$GameId}",
            arguments = listOf(navArgument(GameId) { type = NavType.IntType })
        ) {
            GameDetailsScreen()
        }
    }
}