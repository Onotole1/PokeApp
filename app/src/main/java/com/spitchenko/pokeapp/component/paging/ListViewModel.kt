package com.spitchenko.pokeapp.component.paging

import com.spitchenko.pokeapp.component.lifecycle.SingleLiveEvent
import com.spitchenko.pokeapp.component.messaging.Message

interface ListViewModel<T> {

    val uiModel: PagingUiModel<T>
    val messageEvent: SingleLiveEvent<Message>
    fun showNextPage()
    fun refresh()
    fun retry()
}