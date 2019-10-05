package com.spitchenko.pokeapp.component.extensions

import androidx.lifecycle.MutableLiveData

@Suppress("FunctionName")
fun <T> MutableLiveData(defaultValue: T): MutableLiveData<T> = MutableLiveData<T>().apply {
    value = defaultValue
}