package com.spitchenko.pokeapp.component.paging

interface Paginator<T> {

    val progressItem: T
    suspend fun resetPagingAndGetFirstPage(): List<T>
    suspend fun getNextPage (offset: Int): List<T>
}