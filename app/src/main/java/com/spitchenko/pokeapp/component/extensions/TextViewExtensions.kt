package com.spitchenko.pokeapp.component.extensions

import android.graphics.drawable.Drawable
import android.widget.TextView
import com.spitchenko.pokeapp.component.messaging.Message

fun TextView.setMessage(message: Message?) {
    text = message?.getString(context)
}

fun TextView.setTextIfDifferent(message: Message?) {
    val newText = message?.getString(context)

    if (this.text != newText) {
        this.text = newText
    }
}

fun TextView.setTextIfDifferent(text: CharSequence) {
    if (this.text != text) {
        this.text = text
    }
}

fun TextView.setDrawables(
    drawableStart: Drawable? = null,
    drawableEnd: Drawable? = null,
    drawableTop: Drawable? = null,
    drawableBottom: Drawable? = null
) {
    if (isDrawableDifferent(drawableStart, CompoundDrawables.START)
        || isDrawableDifferent(drawableEnd, CompoundDrawables.END)
        || isDrawableDifferent(drawableTop, CompoundDrawables.TOP)
        || isDrawableDifferent(drawableBottom, CompoundDrawables.BOTTOM)) {
        setCompoundDrawablesRelativeWithIntrinsicBounds(drawableStart, drawableTop, drawableEnd, drawableBottom)
    }
}

private fun TextView.isDrawableDifferent(drawable: Drawable?, compoundDrawable: CompoundDrawables) : Boolean =
    compoundDrawablesRelative[compoundDrawable.ordinal] != drawable

enum class CompoundDrawables {
    START,
    TOP,
    END,
    BOTTOM
}