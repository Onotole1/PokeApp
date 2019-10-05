package com.spitchenko.pokeapp.component.paging

import com.spitchenko.pokeapp.component.extensions.MutableLiveData
import com.spitchenko.pokeapp.component.messaging.Message

class MutablePagingUiModel<T> : PagingUiModel<T> {
    override val emptyProgressVisible = MutableLiveData(false)
    override val emptyErrorVisible = MutableLiveData(false)
    override val emptyError = MutableLiveData<Message?>(null)
    override val emptyVisible = MutableLiveData(false)
    override val emptyText = MutableLiveData<Message?>(null)
    override val dataVisible = MutableLiveData(false)
    override val data = MutableLiveData<List<T>>(emptyList())
    override val errorMessage = MutableLiveData<Message?>(null)
    override val refreshProgressVisible = MutableLiveData(false)
    override val pageErrorVisible = MutableLiveData(false)
}