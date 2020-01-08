package com.spitchenko.pokeapp.component.lifecycle

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface ViewModelCreator {

    fun create(handle: SavedStateHandle): ViewModel
}