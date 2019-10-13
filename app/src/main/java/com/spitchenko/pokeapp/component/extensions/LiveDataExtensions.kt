package com.spitchenko.pokeapp.component.extensions

import androidx.lifecycle.LiveData

fun <T> LiveData<List<T>>.size(): Int = value?.size ?: 0