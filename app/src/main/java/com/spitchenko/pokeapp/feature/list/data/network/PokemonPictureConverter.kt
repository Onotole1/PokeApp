package com.spitchenko.pokeapp.feature.list.data.network

import com.spitchenko.pokeapp.feature.list.data.network.model.PokemonDetailsNetworkDto
import com.spitchenko.pokeapp.feature.list.domain.model.PokemonPicture
import javax.inject.Inject

interface PokemonPictureConverter {

    fun convert(sprites: PokemonDetailsNetworkDto.Sprites): PokemonPicture?
}

class PokemonPictureConverterImpl @Inject constructor(): PokemonPictureConverter {

    override fun convert(sprites: PokemonDetailsNetworkDto.Sprites): PokemonPicture? {
         return PokemonPicture(
            sprites.frontDefault
                ?: sprites.frontShiny
                ?: sprites.frontFemale
                ?: sprites.frontShinyFemale
                ?: sprites.backDefault
                ?: sprites.backShiny
                ?: sprites.backFemale
                ?: sprites.backShinyFemale
                ?: return null
        )
    }
}