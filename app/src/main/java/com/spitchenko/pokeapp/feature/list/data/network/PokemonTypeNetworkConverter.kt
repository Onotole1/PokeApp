package com.spitchenko.pokeapp.feature.list.data.network

import com.spitchenko.pokeapp.feature.list.data.network.model.PokemonDetailsNetworkDto
import com.spitchenko.pokeapp.feature.list.domain.model.Type
import javax.inject.Inject

interface PokemonTypeNetworkConverter {

    fun convert(types: List<PokemonDetailsNetworkDto.Type>): Type
}

class PokemonTypeNetworkConverterImpl @Inject constructor() : PokemonTypeNetworkConverter {

    override fun convert(types: List<PokemonDetailsNetworkDto.Type>): Type =
        Type(types.first().type.name)
}