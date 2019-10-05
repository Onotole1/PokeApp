package com.spitchenko.pokeapp.component.extensions

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<List<T>>.clearAndFill(newItems: Collection<T>) {
    value = value.orEmpty().toMutableList().apply {
        clear()
        addAll(newItems)
    }
}

fun <T> MutableLiveData<List<T>>.addAll(newItems: Collection<T>) {
    value = value.orEmpty().toMutableList().apply {
        addAll(newItems)
    }
}

fun <T> MutableLiveData<List<T>>.add(newItem: T) {
    value = value.orEmpty().toMutableList().apply {
        add(newItem)
    }
}

fun <T> MutableLiveData<List<T>>.removeLast() {
    value = value.orEmpty().toMutableList().apply {
        removeAt(lastIndex)
    }
}

fun <T> MutableLiveData<List<T>>.clear() {
    value = emptyList()
}

inline fun <T> MutableLiveData<List<T>>.transaction(action: MutableList<T>.() -> Unit) {
    value = value.orEmpty().toMutableList().apply {
        action()
    }
}