package com.spitchenko.pokeapp.component.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso

fun ImageView.load(url: String, @DrawableRes placeholderRes: Int = 0, picasso: Picasso) =
    picasso.load(url)
        .also {
            if (placeholderRes != 0) {
                it.placeholder(placeholderRes)
            }
        }
        .into(this)