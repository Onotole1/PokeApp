package com.spitchenko.pokeapp.component.messaging

import android.content.Context
import androidx.annotation.StringRes

data class ResourceFormatted(@StringRes val stringId: Int, val args: List<Any>) : Message {

    override fun getString(context: Context): CharSequence =
        context.getString(stringId, *args.toTypedArray())
}