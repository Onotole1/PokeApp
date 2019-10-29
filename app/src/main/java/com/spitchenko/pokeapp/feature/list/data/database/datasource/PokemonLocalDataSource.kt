package com.spitchenko.pokeapp.feature.list.data.database.datasource

import com.spitchenko.pokeapp.feature.list.data.database.dao.PokemonDao
import com.spitchenko.pokeapp.feature.list.data.database.model.PokemonEntity
import javax.inject.Inject

interface PokemonLocalDataSource {

    suspend fun getPokemons(offset: Int, limit: Int): List<PokemonEntity>
    suspend fun savePokemons(pokemons: List<PokemonEntity>)
    suspend fun clearAndsavePokemons(pokemons: List<PokemonEntity>)

}

class PokemonLocalDataSourceImpl @Inject constructor(
    private val dao: PokemonDao
): PokemonLocalDataSource {

    override suspend fun getPokemons(offset: Int, limit: Int): List<PokemonEntity> =
        dao.getPage(offset, limit)

    override suspend fun savePokemons(pokemons: List<PokemonEntity>) =
        dao.insertOrUpdate(pokemons)

    override suspend fun clearAndsavePokemons(pokemons: List<PokemonEntity>) =
        dao.clearAndInsert(pokemons)
}