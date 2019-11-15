package com.spitchenko.pokeapp.feature.details.presentation

import androidx.lifecycle.ViewModel
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon

class PokemonDetailsViewModel(
    pokemon: Pokemon,
    pokemonDetailsUiConverter: PokemonDetailsUiConverter
) : ViewModel() {

    val uiModel = pokemonDetailsUiConverter.convert(pokemon)
}