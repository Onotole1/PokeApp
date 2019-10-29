package com.spitchenko.pokeapp.component.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<List<T>>.size(): Int = value?.size ?: 0

inline fun <T> LiveData<T>.observe(
    viewLifecycleOwner: LifecycleOwner,
    crossinline action: (T) -> Unit
) = observe(viewLifecycleOwner, Observer {
    action(it)
})