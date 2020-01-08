package com.spitchenko.pokeapp.component.di

import java.lang.ref.WeakReference

class Holder<T : Any> {

    private var _reference : WeakReference<T>? = null
    var reference: T
        get() = requireNotNull(_reference?.get())
        set(value) {
            _reference = WeakReference(value)
        }
}