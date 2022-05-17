package com.example.jetpackcomposemvvm.paging

import java.io.Serializable

data class Paging<out Key, out Value>(
    val previous: Key?,
    val next: Key?,
    val data: List<Value>
) : Serializable
