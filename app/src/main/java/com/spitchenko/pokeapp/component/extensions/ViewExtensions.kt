package com.spitchenko.pokeapp.component.extensions

import android.app.Activity
import android.view.View
import android.view.WindowInsets
import com.spitchenko.pokeapp.component.log.debug

fun View.initNavigateUpClickListener() =
    setOnClickListener {
        (it.context as Activity).onBackPressed()
    }

inline fun View.doOnApplyWindowInsets(crossinline onApplyWindowInsets: (WindowInsets) -> Unit) {

    setOnApplyWindowInsetsListener { _, insets ->
        onApplyWindowInsets(insets)
        insets
    }

    requestApplyInsetsWhenAttached()
}

inline fun View.doOnApplyWindowInsets(crossinline onApplyWindowInsets: (WindowInsets, InitialPadding) -> Unit) {
    val initialPadding = recordInitialPaddingForView(this)

    setOnApplyWindowInsetsListener { _, insets ->
        onApplyWindowInsets(insets, initialPadding)
        insets
    }

    requestApplyInsetsWhenAttached()
}

data class InitialPadding(
    val left: Int, val top: Int,
    val right: Int, val bottom: Int
)

fun recordInitialPaddingForView(view: View) = InitialPadding(
    view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom
)

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        debug("$this attached. Request insets")
        requestApplyInsets()
    } else {
        debug("$this not attached. Set attach listener")
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(view: View) {
                debug("$this attached inside listener. Request insets")
                view.removeOnAttachStateChangeListener(this)
                view.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

fun View.setFullScreen() {
    systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
}