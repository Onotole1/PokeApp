package com.spitchenko.pokeapp.feature.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.spitchenko.pokeapp.component.lifecycle.delegate
import com.spitchenko.pokeapp.feature.details.presentation.model.PokemonDetailsUiModel
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon

class PokemonDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    pokemon: Pokemon,
    pokemonDetailsUiConverter: PokemonDetailsUiConverter
) : ViewModel() {

    val uiModel: PokemonDetailsUiModel by savedStateHandle.delegate {
        pokemonDetailsUiConverter.convert(pokemon)
    }
}