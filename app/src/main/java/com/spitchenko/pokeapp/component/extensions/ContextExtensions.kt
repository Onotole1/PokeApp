package com.spitchenko.pokeapp.component.extensions

import android.content.Context
import android.widget.Toast
import com.spitchenko.pokeapp.component.messaging.Message
import com.spitchenko.pokeapp.component.messaging.toCharSequence

fun Context.showMessage(message: Message, duration: Duration = Duration.LONG) {
    val toastDuration = when (duration) {
        Duration.LONG -> Toast.LENGTH_LONG
        Duration.SHORT -> Toast.LENGTH_SHORT
    }

    Toast.makeText(this, message.toCharSequence(this), toastDuration).show()
}

enum class Duration {
    SHORT,
    LONG
}