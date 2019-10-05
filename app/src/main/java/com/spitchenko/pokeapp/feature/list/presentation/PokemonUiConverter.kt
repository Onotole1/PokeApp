package com.spitchenko.pokeapp.feature.list.presentation

import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonUiModel
import com.squareup.picasso.Picasso

interface PokemonUiConverter {

    fun convert(pokemon: Pokemon): PokemonUiModel
}

class PokemonUiConverterImpl(private val picasso: Picasso): PokemonUiConverter {

    override fun convert(pokemon: Pokemon): PokemonUiModel = PokemonUiModel(pokemon, picasso)
}