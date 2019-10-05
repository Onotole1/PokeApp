package com.spitchenko.pokeapp.component.paging

import com.spitchenko.pokeapp.component.messaging.Message

interface Paginator<T> {

    fun progressItem(): T
    fun errorItem(message: Message): T
    suspend fun resetPagingAndGetFirstPage(): List<T>
    suspend fun getNextPage (offset: Int): List<T>
}