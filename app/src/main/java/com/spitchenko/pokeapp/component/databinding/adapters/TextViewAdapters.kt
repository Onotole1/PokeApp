package com.spitchenko.pokeapp.component.databinding.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.spitchenko.pokeapp.component.messaging.Message
import com.spitchenko.pokeapp.component.messaging.toCharSequence

@BindingAdapter("android:text")
fun TextView.setMessage(message: Message?) {
    text = message?.toCharSequence(context)
}