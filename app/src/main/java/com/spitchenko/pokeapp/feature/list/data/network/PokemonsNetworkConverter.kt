package com.spitchenko.pokeapp.feature.list.data.network

import com.spitchenko.pokeapp.feature.list.data.network.model.PokemonsListNetworkDto
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import javax.inject.Inject

interface PokemonsNetworkConverter {

    fun convert(response: PokemonsListNetworkDto): List<Pokemon>
}

class PokemonsNetworkConverterImpl @Inject constructor() : PokemonsNetworkConverter {

    override fun convert(response: PokemonsListNetworkDto): List<Pokemon> =
        response.results.map { dto ->
            Pokemon(dto.name)
        }
}