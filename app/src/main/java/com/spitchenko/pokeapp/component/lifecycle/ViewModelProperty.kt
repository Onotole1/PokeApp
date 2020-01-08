package com.spitchenko.pokeapp.component.lifecycle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kotlin.reflect.KClass

class ViewModelProperty<VM : ViewModel>(
    private val viewModelClass: KClass<VM>,
    private val ownerProducer: () -> ViewModelStoreOwner,
    private val factoryProducer: () -> ViewModelProvider.Factory
) : Lazy<VM> {

    private var cached: VM? = null

    override val value: VM
        get() {
            return cached
                ?: ViewModelProvider(ownerProducer(), factoryProducer()).get(viewModelClass.java).also {
                    cached = it
                }
        }

    override fun isInitialized() = cached != null
}