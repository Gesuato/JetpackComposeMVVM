package com.example.jetpackcomposemvvm.presentation.composables

import androidx.navigation.NavHostController
import com.example.jetpackcomposemvvm.presentation.composables.Destinations.GameDetails
import com.example.jetpackcomposemvvm.presentation.composables.Destinations.Home

object Destinations {
    const val Home = "home"
    const val GameDetails = "gameDetails"

    object GameDetailsArgs {
        const val GameId = "gameId"
    }
}

class Actions(navHostController: NavHostController) {
    val openGameDetails: (Int) -> Unit = { gameId ->
        navHostController.popBackStack(route = Home, inclusive = false)
        navHostController.navigate("$GameDetails/$gameId")
    }

    val navigateBack: () -> Unit = {
        navHostController.navigateUp()
    }
}