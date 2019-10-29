package com.spitchenko.pokeapp.component.extensions

import android.view.View

fun View.setVisibleOrGone(visibleOrGone: Boolean) {
    if (visibleOrGone) {
        if (visibility != View.VISIBLE) {
            visibility = View.VISIBLE
        }
    } else {
        if (visibility != View.GONE) {
            visibility = View.GONE
        }
    }
}

fun View.setClickableAndFocusable(clickable: Boolean) {

    if (isClickable != clickable) {
        isClickable = clickable
    }
    if (isFocusable != clickable) {
        isFocusable = clickable
    }
}