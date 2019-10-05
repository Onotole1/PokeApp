package com.spitchenko.pokeapp.feature.list.presentation

import androidx.lifecycle.ViewModel
import com.spitchenko.pokeapp.component.paging.ListViewModel
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import kotlinx.coroutines.Job

class PokemonListViewModel(
    private val viewModelJob: Job,
    delegate: ListViewModel<BindingClass>
) : ViewModel(), ListViewModel<BindingClass> by delegate {

    override fun onCleared() {
        viewModelJob.cancel()
    }
}