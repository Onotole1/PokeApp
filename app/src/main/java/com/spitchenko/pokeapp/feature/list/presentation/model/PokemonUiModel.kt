package com.spitchenko.pokeapp.feature.list.presentation.model

import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewType
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewTypeOwner

data class PokemonUiModel(
    val pokemonState: PokemonState
): BindingClass, ViewTypeOwner by PokemonUiModel {

    companion object : ViewTypeOwner {
        override val viewType: ViewType = ViewType(R.layout.item_pokemon)
    }
}