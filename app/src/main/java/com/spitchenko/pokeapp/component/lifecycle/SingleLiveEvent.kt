package com.spitchenko.pokeapp.component.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

interface SingleLiveEvent<T> {

    fun observe(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit)
}

interface SingleLiveEventTarget<T> {

    fun sendEvent(event: T)
}

class MutableSingleLiveEvent<T> : SingleLiveEvent<T>, SingleLiveEventTarget<T>, LifecycleObserver {

    private var observer: ((T) -> Unit)? = null
    private var boundObserver: ((T) -> Unit)? = null

    private var buffer: T? = null

    override fun sendEvent(event: T) {
        val boundObserver = boundObserver

        if (boundObserver != null) {
            boundObserver(event)
        } else {
            buffer = event
        }
    }

    override fun observe(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
        this.observer = observer
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun connect() {
        val observer = observer
        if (observer != null) {
            boundObserver = observer

            val buffer = buffer
            if (buffer != null) {
                observer(buffer)
                this.buffer = null
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun disconnect() {
        boundObserver = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        observer = null
    }
}