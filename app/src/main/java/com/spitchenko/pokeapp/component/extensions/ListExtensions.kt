package com.spitchenko.pokeapp.component.extensions

inline fun <T, reified OUT> List<T>.getAsInstanceAt(index: Int): OUT = get(index) as OUT