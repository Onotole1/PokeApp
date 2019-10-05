package com.spitchenko.pokeapp.component.extensions

import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.messaging.Message
import com.spitchenko.pokeapp.component.messaging.PreparedString
import com.spitchenko.pokeapp.component.messaging.Resource
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun Throwable.toUserFriendlyError(): Message {
    return when (this) {
        is ConnectException,
        is SocketTimeoutException,
        is TimeoutException,
        is UnknownHostException -> Resource(R.string.connection_error)
        is IOException -> Resource(R.string.no_disk_space_error)
        else -> PreparedString(localizedMessage)
    }
}