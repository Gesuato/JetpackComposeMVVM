package com.example.jetpackcomposemvvm.remote.model

import com.example.jetpackcomposemvvm.domain.entity.Game
import com.google.gson.annotations.SerializedName

data class GameApiResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("rating")
    val rating: Double
)

fun GameApiResult.toEntity() = Game(id, name, backgroundImage, rating)

fun List<GameApiResult>.toEntity() = map { it.toEntity() }
