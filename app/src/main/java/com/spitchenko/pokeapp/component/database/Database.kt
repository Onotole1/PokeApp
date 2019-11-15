package com.spitchenko.pokeapp.component.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spitchenko.pokeapp.feature.list.data.database.dao.PokemonDao
import com.spitchenko.pokeapp.feature.list.data.database.model.PokemonEntity

const val DATABASE_NAME = "pokemon_database"
private const val DATABASE_VERSION = 1

@Database(
    entities = [
        PokemonEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = true
)
abstract class Database : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao
}