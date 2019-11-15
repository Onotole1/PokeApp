package com.spitchenko.pokeapp.feature.list.data.repository

import com.spitchenko.pokeapp.feature.list.data.database.datasource.PokemonLocalDataSource
import com.spitchenko.pokeapp.feature.list.data.database.model.PokemonEntity
import com.spitchenko.pokeapp.feature.list.data.database.toEntity
import com.spitchenko.pokeapp.feature.list.data.database.toPokemon
import com.spitchenko.pokeapp.feature.list.data.network.PokemonDetailsNetworkConverter
import com.spitchenko.pokeapp.feature.list.data.network.datasource.PokemonDetailsNetworkDataSource
import com.spitchenko.pokeapp.feature.list.data.network.datasource.PokemonsNetworkDataSource
import com.spitchenko.pokeapp.feature.list.data.network.model.PokemonNetworkDto
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import com.spitchenko.pokeapp.feature.list.domain.usecase.PokemonsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class PokemonsRepositoryImpl @Inject constructor(
    private val pokemonDetailsNetworkDataSource: PokemonDetailsNetworkDataSource,
    private val pokemonDetailsNetworkConverter: PokemonDetailsNetworkConverter,
    private val networkDataSource: PokemonsNetworkDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : PokemonsRepository {

    override suspend fun getPokemons(pageSize: Int, offset: Int): List<Pokemon> {
        val localPokemons = pokemonLocalDataSource.getPokemons(offset.dec(), pageSize)

        if (localPokemons.isNotEmpty()) {
            return localPokemons.map(PokemonEntity::toPokemon)
        }

        return loadPokemonsFromNetwork(offset = offset, pageSize = pageSize) {
            pokemonLocalDataSource.savePokemons(it)
        }
    }

    override suspend fun refreshAndGetFirstPage(pageSize: Int): List<Pokemon> =
        loadPokemonsFromNetwork(offset = 0, pageSize = pageSize) {
            pokemonLocalDataSource.clearAndsavePokemons(it)
        }

    private suspend inline fun loadPokemonsFromNetwork(
        offset: Int,
        pageSize: Int,
        crossinline onSuccess: suspend (pokemons: List<PokemonEntity>) -> Unit
    ): List<Pokemon> = coroutineScope {
        val pokemons =
            networkDataSource.getPokemons(offset = offset, limit = pageSize).results.map {
                async {
                    loadDetails(it)
                }
            }.map {
                it.await()
            }

        onSuccess(pokemons.map(Pokemon::toEntity))

        pokemons
    }

    private suspend inline fun loadDetails(pokemonNetworkDto: PokemonNetworkDto): Pokemon =
        pokemonDetailsNetworkConverter.convert(
            pokemonDetailsNetworkDataSource.getPokemonDetailsByName(
                pokemonNetworkDto.name
            )
        )
}