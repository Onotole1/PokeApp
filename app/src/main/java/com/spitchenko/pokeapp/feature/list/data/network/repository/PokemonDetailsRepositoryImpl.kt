package com.spitchenko.pokeapp.feature.list.data.network.repository

import com.spitchenko.pokeapp.feature.list.data.network.PokemonDetailsNetworkConverter
import com.spitchenko.pokeapp.feature.list.data.network.datasource.PokemonDetailsNetworkDataSource
import com.spitchenko.pokeapp.feature.list.domain.model.PokemonDetails
import com.spitchenko.pokeapp.feature.list.domain.usecase.PokemonDetailsRepository
import javax.inject.Inject

class PokemonDetailsRepositoryImpl @Inject constructor(
    private val networkDataSource: PokemonDetailsNetworkDataSource,
    private val networkDetailsNetworkConverter: PokemonDetailsNetworkConverter
) : PokemonDetailsRepository {

    override suspend fun getDetailsByName(name: String): PokemonDetails =
        networkDetailsNetworkConverter.convert(
            networkDataSource.getPokemonDetailsByName(name)
        )
}