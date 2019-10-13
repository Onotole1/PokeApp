package com.spitchenko.pokeapp.feature.list.data.network.datasource

import com.spitchenko.pokeapp.feature.list.data.network.api.PokemonDetailsApi
import com.spitchenko.pokeapp.feature.list.data.network.model.PokemonDetailsNetworkDto
import javax.inject.Inject

interface PokemonDetailsNetworkDataSource {

    suspend fun getPokemonDetailsByName(name: String): PokemonDetailsNetworkDto
}

class PokemonDetailsNetworkDataSourceImpl @Inject constructor(
    private val api: PokemonDetailsApi
) : PokemonDetailsNetworkDataSource {

    override suspend fun getPokemonDetailsByName(name: String): PokemonDetailsNetworkDto =
        api.getPokemonDetails(name)
}