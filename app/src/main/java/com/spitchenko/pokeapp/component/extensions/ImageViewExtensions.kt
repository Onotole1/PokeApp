package com.spitchenko.pokeapp.component.extensions

import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.spitchenko.pokeapp.component.glide.GlideApp

fun ImageView.setImageGlide(
    imageUrl: String?,
    imagePlaceholder: Int?
) {

    val requestOptions = RequestOptions.centerCropTransform()
        .placeholder(imagePlaceholder ?: 0)

    GlideApp.with(this)
        .load(imageUrl)
        .apply(requestOptions)
        .into(this)
}