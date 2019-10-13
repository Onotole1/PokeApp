package com.spitchenko.pokeapp.feature.details.presentation

import androidx.lifecycle.ViewModel
import com.spitchenko.pokeapp.feature.list.domain.model.PokemonDetails

class PokemonDetailsViewModel(
    pokemonDetails: PokemonDetails,
    pokemonDetailsUiConverter: PokemonDetailsUiConverter
) : ViewModel() {

    val uiModel = pokemonDetailsUiConverter.convert(pokemonDetails)
}