package com.spitchenko.pokeapp.component.messaging

import android.content.Context

data class PreparedString(val string: CharSequence) : Message {

    override fun getString(context: Context): CharSequence = string
}