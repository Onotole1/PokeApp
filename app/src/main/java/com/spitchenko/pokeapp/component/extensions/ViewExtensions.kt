package com.spitchenko.pokeapp.component.extensions

import android.view.View
import android.view.WindowInsets
import androidx.navigation.findNavController

fun View.initNavigateUpClickListener() =
    setOnClickListener {
        it.findNavController().navigateUp()
    }

inline fun View.doOnApplyWindowInsets(crossinline onApplyWindowInsets: (WindowInsets) -> Unit) {

    setOnApplyWindowInsetsListener { _, insets ->
        onApplyWindowInsets(insets)
        insets
    }

    requestApplyInsetsWhenAttached()
}

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(view: View) {
                view.removeOnAttachStateChangeListener(this)
                view.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}