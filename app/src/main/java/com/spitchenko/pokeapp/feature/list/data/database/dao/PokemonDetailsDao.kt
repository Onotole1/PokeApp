package com.spitchenko.pokeapp.feature.list.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.spitchenko.pokeapp.component.database.BaseDao
import com.spitchenko.pokeapp.feature.list.data.database.model.PokemonDetailsEntity

@Dao
interface PokemonDetailsDao : BaseDao<PokemonDetailsEntity> {

    @Query("SELECT * FROM pokemon_details WHERE name = :name")
    suspend fun getByName(name: String): PokemonDetailsEntity?
}