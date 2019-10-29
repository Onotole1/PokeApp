package com.spitchenko.pokeapp.feature.list.data.database.datasource

import com.spitchenko.pokeapp.feature.list.data.database.dao.PokemonDetailsDao
import com.spitchenko.pokeapp.feature.list.data.database.model.PokemonDetailsEntity
import javax.inject.Inject

interface PokemonDetailsLocalDataSource {

    suspend fun getPokemonDetailsByName(name: String): PokemonDetailsEntity?
    suspend fun savePokemonDetails(pokemonDetailsEntity: PokemonDetailsEntity)
}

class PokemonDetailsLocalDataSourceImpl @Inject constructor(
    private val dao: PokemonDetailsDao
) : PokemonDetailsLocalDataSource {

    override suspend fun getPokemonDetailsByName(name: String): PokemonDetailsEntity? =
        dao.getByName(name)

    override suspend fun savePokemonDetails(pokemonDetailsEntity: PokemonDetailsEntity) =
        dao.insertOrUpdate(pokemonDetailsEntity)
}