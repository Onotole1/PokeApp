package com.spitchenko.pokeapp.feature.list.data.network

import com.spitchenko.pokeapp.feature.list.data.network.model.PokemonsListNetworkDto
import javax.inject.Inject

interface PokemonsNetworkDataSource {

    suspend fun getPokemons(offset: Int, limit: Int): PokemonsListNetworkDto
}

class PokemonsNetworkDataSourceImpl @Inject constructor(
    private val api: PokemonListApi
) : PokemonsNetworkDataSource {

    override suspend fun getPokemons(offset: Int, limit: Int): PokemonsListNetworkDto =
        api.getPokemons(offset, limit)
}