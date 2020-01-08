package com.spitchenko.pokeapp.component.messaging

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


sealed class Message: Parcelable

@Parcelize
data class PreparedString(val string: CharSequence): Message()
@Parcelize
data class Resource(@StringRes val stringId: Int) : Message()
@Parcelize
data class ResourceFormatted(@StringRes val stringId: Int, val args: List<Serializable>) : Message()

fun Message.toCharSequence(context: Context): CharSequence =
    when (this) {
        is PreparedString -> string
        is Resource -> context.getString(stringId)
        is ResourceFormatted -> context.getString(stringId, *args.toTypedArray())
    }