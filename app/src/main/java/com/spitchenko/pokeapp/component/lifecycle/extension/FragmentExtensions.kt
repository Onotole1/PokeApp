package com.spitchenko.pokeapp.component.lifecycle.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.spitchenko.pokeapp.component.lifecycle.ViewModelFactory
import com.spitchenko.pokeapp.component.lifecycle.ViewModelProperty
import com.spitchenko.pokeapp.component.lifecycle.ViewModelProviders

inline fun <reified VM : ViewModel> Fragment.viewModels(
    viewModelProviders: ViewModelProviders,
    noinline ownerProducer: () -> ViewModelStoreOwner = { this }
): ViewModelProperty<VM> =
    ViewModelProperty(
        VM::class,
        ownerProducer
    ) {
        ViewModelFactory(viewModelProviders, this)
    }