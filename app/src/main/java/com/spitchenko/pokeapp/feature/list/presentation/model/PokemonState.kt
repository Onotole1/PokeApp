package com.spitchenko.pokeapp.feature.list.presentation.model

import com.spitchenko.pokeapp.component.messaging.Message
import com.spitchenko.pokeapp.feature.list.domain.model.PokemonDetails

sealed class PokemonState {
    abstract val name: String

    data class Data(val details: PokemonDetails): PokemonState() {
        override val name: String = details.name
    }

    data class Progress(override val name: String): PokemonState()

    data class Error(override val name: String, val message: Message): PokemonState()
}