package com.example.jetpackcomposemvvm.paging

import java.io.Serializable

data class PagingResult<out Key, out ValueDto>(
    val previous: Key?,
    val next: Key?,
    val results: List<ValueDto>
) : Serializable {
    internal fun <Value> entity(mapper: (List<ValueDto>) -> (List<Value>)): Paging<Key, Value> =
        Paging(previous, next, mapper(results))
}
