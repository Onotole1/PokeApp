package com.spitchenko.pokeapp.feature.list.data.database.model

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "pokemon",
    indices = [
        Index("name")
    ],
    primaryKeys = [
        "name"
    ]
)
data class PokemonEntity(
    val name: String,
    val height: Int,
    val weight: Int,
    val attack: Int,
    val defense: Int,
    val health: Int,
    val type: String,
    val image: String?
)