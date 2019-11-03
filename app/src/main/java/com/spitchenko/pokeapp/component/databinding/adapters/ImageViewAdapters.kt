package com.spitchenko.pokeapp.component.databinding.adapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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

@BindingAdapter(
    value = [
        "imageUrl",
        "imagePlaceholder",
        "imageHeight",
        "imageWidth"
    ]
)
fun ImageView.setImageGlide(
    imageUrl: String,
    imagePlaceholder: Int,
    imageHeight: Float,
    imageWidth: Float
) {
    GlideApp.with(this)
        .asBitmap()
        .load(imageUrl)
        .placeholder(imagePlaceholder)
        .into(object : CustomTarget<Bitmap>(imageWidth.toInt(), imageHeight.toInt()) {
            override fun onLoadCleared(placeholder: Drawable?) = Unit

            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                setImageBitmap(resource)
            }
        })
}