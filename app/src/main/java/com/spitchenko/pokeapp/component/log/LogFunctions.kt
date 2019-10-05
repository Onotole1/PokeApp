package com.spitchenko.pokeapp.component.log

import timber.log.Timber

fun debug(message: String) = Timber.d(message)

fun info(message: String) = Timber.i(message)