package com.spitchenko.pokeapp.feature.list.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.spitchenko.pokeapp.component.database.BaseDao
import com.spitchenko.pokeapp.feature.list.data.database.model.PokemonEntity

@Dao
abstract class PokemonDao: BaseDao<PokemonEntity> {

    @Query("SELECT * FROM pokemon limit :limit offset :offset")
    abstract suspend fun getPage(offset: Int, limit: Int): List<PokemonEntity>

    @Query(value = "DELETE FROM pokemon")
    abstract suspend fun deleteAll()

    @Transaction
    open suspend fun clearAndInsert(pokemons: List<PokemonEntity>) {
        deleteAll()
        insert(pokemons)
    }
}