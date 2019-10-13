package com.spitchenko.pokeapp.feature.details.presentation

import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.messaging.ResourceFormatted
import com.spitchenko.pokeapp.feature.details.presentation.model.PokemonDetailUiModel
import com.spitchenko.pokeapp.feature.details.presentation.model.PokemonDetailsUiModel
import com.spitchenko.pokeapp.feature.list.domain.model.PokemonDetails
import javax.inject.Inject

interface PokemonDetailsUiConverter {

    fun convert(pokemonDetails: PokemonDetails): PokemonDetailsUiModel
}

class PokemonDetailsUiConverterImpl @Inject constructor() : PokemonDetailsUiConverter {

    override fun convert(pokemonDetails: PokemonDetails): PokemonDetailsUiModel {
        val details = listOf(
            PokemonDetailUiModel(
                ResourceFormatted(
                    R.string.pokemon_details_height,
                    listOf(pokemonDetails.height.value)
                )
            ),
            PokemonDetailUiModel(
                ResourceFormatted(
                    R.string.pokemon_details_weight,
                    listOf(pokemonDetails.weight.value)
                )
            ),
            PokemonDetailUiModel(
                ResourceFormatted(R.string.pokemon_details_type, listOf(pokemonDetails.type.value))
            ),
            PokemonDetailUiModel(
                ResourceFormatted(
                    R.string.pokemon_details_attack,
                    listOf(pokemonDetails.attack.value)
                )
            ),
            PokemonDetailUiModel(
                ResourceFormatted(
                    R.string.pokemon_details_defense,
                    listOf(pokemonDetails.defense.value)
                )
            ),
            PokemonDetailUiModel(
                ResourceFormatted(
                    R.string.pokemon_details_health,
                    listOf(pokemonDetails.health.value)
                )
            )
        )

        return PokemonDetailsUiModel(
            pokemonDetails.name,
            pokemonDetails.image?.url,
            details
        )
    }
}