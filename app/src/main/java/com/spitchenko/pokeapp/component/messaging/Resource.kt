package com.spitchenko.pokeapp.component.messaging

import android.content.Context
import androidx.annotation.StringRes

data class Resource(@StringRes val stringId: Int) : Message {

    override fun getString(context: Context): CharSequence = context.getString(stringId)
}