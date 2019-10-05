package com.spitchenko.pokeapp.feature.list.data.network

import com.spitchenko.pokeapp.feature.list.data.network.model.PokemonsListNetworkDto
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import javax.inject.Inject

interface PokemonsNetworkConverter {

    fun convert(response: PokemonsListNetworkDto): List<Pokemon>
}

private const val IMAGE_URL =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
private const val URL_DIVIDER = '/'
private const val IMAGE_SUFFIX = ".png"

class PokemonsNetworkConverterImpl @Inject constructor() : PokemonsNetworkConverter {

    override fun convert(response: PokemonsListNetworkDto): List<Pokemon> =
        response.results.map { dto ->
            val pokemonId = dto.url.split(URL_DIVIDER)
                .filterNot {
                    it.isBlank()
                }.last()

            Pokemon(dto.name, IMAGE_URL + pokemonId + IMAGE_SUFFIX)
        }
}