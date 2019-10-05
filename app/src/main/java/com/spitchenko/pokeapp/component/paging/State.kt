package com.spitchenko.pokeapp.component.paging

import androidx.annotation.CallSuper
import com.spitchenko.pokeapp.component.log.debug
import com.spitchenko.pokeapp.component.messaging.Message

interface State<T> {

    @CallSuper
    fun refresh() = debug("refresh. State: ${this::class.java.simpleName}")
    @CallSuper
    fun showNextPage() = debug("showNextPage. State: ${this::class.java.simpleName}")
    @CallSuper
    fun newData(data: List<T>) = debug("newData. State: ${this::class.java.simpleName}")
    @CallSuper
    fun fail(message: Message) = debug("fail. State: ${this::class.java.simpleName}")
}