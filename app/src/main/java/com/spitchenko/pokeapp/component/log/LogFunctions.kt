@file:Suppress("NOTHING_TO_INLINE")

package com.spitchenko.pokeapp.component.log

import timber.log.Timber

inline fun debug(message: String) = Timber.d(message)

inline fun info(message: String) = Timber.i(message)