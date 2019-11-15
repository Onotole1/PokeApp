package com.spitchenko.pokeapp.feature.details.presentation

import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.messaging.ResourceFormatted
import com.spitchenko.pokeapp.feature.details.presentation.model.PokemonDetailUiModel
import com.spitchenko.pokeapp.feature.details.presentation.model.PokemonDetailsUiModel
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import javax.inject.Inject

interface PokemonDetailsUiConverter {

    fun convert(pokemon: Pokemon): PokemonDetailsUiModel
}

class PokemonDetailsUiConverterImpl @Inject constructor() : PokemonDetailsUiConverter {

    override fun convert(pokemon: Pokemon): PokemonDetailsUiModel {
        val details = listOf(
            PokemonDetailUiModel(
                ResourceFormatted(
                    R.string.pokemon_details_height,
                    listOf(pokemon.height.value)
                )
            ),
            PokemonDetailUiModel(
                ResourceFormatted(
                    R.string.pokemon_details_weight,
                    listOf(pokemon.weight.value)
                )
            ),
            PokemonDetailUiModel(
                ResourceFormatted(R.string.pokemon_details_type, listOf(pokemon.type.value))
            ),
            PokemonDetailUiModel(
                ResourceFormatted(
                    R.string.pokemon_details_attack,
                    listOf(pokemon.attack.value)
                )
            ),
            PokemonDetailUiModel(
                ResourceFormatted(
                    R.string.pokemon_details_defense,
                    listOf(pokemon.defense.value)
                )
            ),
            PokemonDetailUiModel(
                ResourceFormatted(
                    R.string.pokemon_details_health,
                    listOf(pokemon.health.value)
                )
            )
        )

        return PokemonDetailsUiModel(
            pokemon.name,
            pokemon.image?.url,
            details
        )
    }
}