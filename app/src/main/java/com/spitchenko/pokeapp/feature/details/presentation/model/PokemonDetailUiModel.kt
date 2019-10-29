package com.spitchenko.pokeapp.feature.details.presentation.model

import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.messaging.Message
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewType
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewTypeOwner

data class PokemonDetailUiModel(
    val text: Message
) : BindingClass, ViewTypeOwner by PokemonDetailUiModel {

    companion object : ViewTypeOwner {
        override val viewType: ViewType = ViewType(R.layout.item_pokemon_detail)
    }
}