package com.example.jetpackcomposemvvm.presentation.composables.commonui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingSpinner() {
    Box {
        CircularProgressIndicator(progress = 1f, color = Color.DarkGray)
        CircularProgressIndicator(color = Color.Gray)
    }
}