package com.spitchenko.pokeapp.component.databinding.adapters

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.spitchenko.pokeapp.component.extensions.load
import com.squareup.picasso.Picasso

@BindingAdapter("load", "placeholder", "picasso", requireAll = true)
fun ImageView.setLoad(url: String, @DrawableRes placeholder: Int, picasso: Picasso) {
    if (url.isNotBlank()) {
        load(url, placeholder, picasso)
    } else {
        setImageResource(placeholder)
    }
}