package com.spitchenko.pokeapp.component.databinding.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import com.spitchenko.pokeapp.component.glide.GlideApp

@BindingAdapter(
    value = [
        "imageUrl",
        "imagePlaceholder"
    ],
    requireAll = false
)
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