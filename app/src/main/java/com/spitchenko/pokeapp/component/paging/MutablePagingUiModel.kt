package com.spitchenko.pokeapp.component.paging

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.spitchenko.pokeapp.component.lifecycle.savedStateLiveData
import com.spitchenko.pokeapp.component.messaging.Message

class MutablePagingUiModel<T : Parcelable>(savedStateHandle: SavedStateHandle) : PagingUiModel<T> {
    override val emptyProgressVisible by savedStateHandle.savedStateLiveData(false)
    override val emptyErrorVisible by savedStateHandle.savedStateLiveData(false)
    override val emptyError by savedStateHandle.savedStateLiveData<Message>()
    override val emptyVisible by savedStateHandle.savedStateLiveData(false)
    override val emptyText by savedStateHandle.savedStateLiveData<Message>()
    override val dataVisible by savedStateHandle.savedStateLiveData(false)
    override val data by savedStateHandle.savedStateLiveData<List<T>>(emptyList())
    override val refreshProgressVisible by savedStateHandle.savedStateLiveData(false)
}