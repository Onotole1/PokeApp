package com.spitchenko.pokeapp.component.databinding.adapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(visibleOrGone: Boolean) {
    visibility = if (visibleOrGone) {
        View.VISIBLE
    } else {
        View.GONE
    }
}