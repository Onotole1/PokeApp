package com.spitchenko.pokeapp.feature.list.data.repository

import com.spitchenko.pokeapp.feature.list.data.database.datasource.PokemonDetailsLocalDataSource
import com.spitchenko.pokeapp.feature.list.data.database.model.PokemonDetailsEntity
import com.spitchenko.pokeapp.feature.list.data.database.toDetails
import com.spitchenko.pokeapp.feature.list.data.database.toEntity
import com.spitchenko.pokeapp.feature.list.data.network.PokemonDetailsNetworkConverter
import com.spitchenko.pokeapp.feature.list.data.network.datasource.PokemonDetailsNetworkDataSource
import com.spitchenko.pokeapp.feature.list.domain.model.PokemonDetails
import com.spitchenko.pokeapp.feature.list.domain.usecase.PokemonDetailsRepository
import javax.inject.Inject

class PokemonDetailsRepositoryImpl @Inject constructor(
    private val networkDataSource: PokemonDetailsNetworkDataSource,
    private val localDataSource: PokemonDetailsLocalDataSource,
    private val networkDetailsNetworkConverter: PokemonDetailsNetworkConverter
) : PokemonDetailsRepository {

    override suspend fun getDetailsByName(name: String): PokemonDetails {

        val localPokemon =
            localDataSource.getPokemonDetailsByName(name)?.let(PokemonDetailsEntity::toDetails)

        if (localPokemon != null) {
            return localPokemon
        }

        val networkPokemon = networkDetailsNetworkConverter.convert(
            networkDataSource.getPokemonDetailsByName(name)
        )

        localDataSource.savePokemonDetails(networkPokemon.toEntity())

        return networkPokemon
    }
}