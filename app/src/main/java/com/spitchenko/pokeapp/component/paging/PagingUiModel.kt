package com.spitchenko.pokeapp.component.paging

import androidx.lifecycle.LiveData
import com.spitchenko.pokeapp.component.messaging.Message

interface PagingUiModel<T> {
    val emptyProgressVisible: LiveData<Boolean>
    val emptyErrorVisible: LiveData<Boolean>
    val emptyError: LiveData<Message?>
    val emptyVisible: LiveData<Boolean>
    val emptyText: LiveData<Message?>
    val dataVisible: LiveData<Boolean>
    val data: LiveData<List<T>>
    val errorMessage: LiveData<Message?>
    val refreshProgressVisible: LiveData<Boolean>
    val pageErrorVisible: LiveData<Boolean>
}