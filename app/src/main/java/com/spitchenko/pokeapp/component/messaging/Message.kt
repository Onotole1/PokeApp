package com.spitchenko.pokeapp.component.messaging

import android.content.Context

interface Message {

    fun getString(context: Context): CharSequence
}