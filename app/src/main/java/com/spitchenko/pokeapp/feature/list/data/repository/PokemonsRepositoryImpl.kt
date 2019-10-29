package com.spitchenko.pokeapp.feature.list.data.repository

import com.spitchenko.pokeapp.feature.list.data.database.datasource.PokemonLocalDataSource
import com.spitchenko.pokeapp.feature.list.data.database.model.PokemonEntity
import com.spitchenko.pokeapp.feature.list.data.database.toEntity
import com.spitchenko.pokeapp.feature.list.data.database.toPokemon
import com.spitchenko.pokeapp.feature.list.data.network.PokemonsNetworkConverter
import com.spitchenko.pokeapp.feature.list.data.network.datasource.PokemonsNetworkDataSource
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import com.spitchenko.pokeapp.feature.list.domain.usecase.PokemonsRepository
import javax.inject.Inject

class PokemonsRepositoryImpl @Inject constructor(
    private val networkDataSource: PokemonsNetworkDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val pokemonsNetworkConverter: PokemonsNetworkConverter
) : PokemonsRepository {

    override suspend fun getPokemons(pageSize: Int, offset: Int): List<Pokemon> =
        getPokemonsInternal(pageSize, offset) {
            pokemonLocalDataSource.savePokemons(it.map(Pokemon::toEntity))
        }

    override suspend fun refreshAndGetFirstPage(pageSize: Int): List<Pokemon> =
        getPokemonsInternal(pageSize = pageSize, offset = 0) {
            pokemonLocalDataSource.clearAndsavePokemons(it.map(Pokemon::toEntity))
        }

    private suspend inline fun getPokemonsInternal(
        pageSize: Int,
        offset: Int,
        crossinline onNetworkSuccess: suspend (List<Pokemon>) -> Unit
    ): List<Pokemon> {
        val localPokemons = pokemonLocalDataSource.getPokemons(offset.dec(), pageSize)

        if (localPokemons.isNotEmpty()) {
            return localPokemons.map(PokemonEntity::toPokemon)
        }

        val networkPokemons = networkDataSource.getPokemons(offset = offset, limit = pageSize)
            .let(pokemonsNetworkConverter::convert)

        onNetworkSuccess(networkPokemons)

        return networkPokemons
    }
}