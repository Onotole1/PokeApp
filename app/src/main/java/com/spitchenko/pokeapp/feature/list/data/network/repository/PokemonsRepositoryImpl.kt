package com.spitchenko.pokeapp.feature.list.data.network.repository

import com.spitchenko.pokeapp.feature.list.data.network.PokemonsNetworkConverter
import com.spitchenko.pokeapp.feature.list.data.network.datasource.PokemonsNetworkDataSource
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import com.spitchenko.pokeapp.feature.list.domain.usecase.PokemonsRepository
import javax.inject.Inject

class PokemonsRepositoryImpl @Inject constructor(
    private val networkDataSource: PokemonsNetworkDataSource,
    private val pokemonsNetworkConverter: PokemonsNetworkConverter
) : PokemonsRepository {

    override suspend fun getPokemons(pageSize: Int, offset: Int): List<Pokemon> =
        networkDataSource.getPokemons(offset = offset, limit = pageSize)
            .let(pokemonsNetworkConverter::convert)

    override suspend fun refreshAndGetFirstPage(pageSize: Int): List<Pokemon> =
        networkDataSource.getPokemons(offset = 0, limit = pageSize)
            .let(pokemonsNetworkConverter::convert)
}