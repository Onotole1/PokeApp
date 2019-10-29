package com.spitchenko.pokeapp.feature.list.data.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "pokemon_details",
    indices = [
        Index("name")
    ],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["name"],
            childColumns = ["name"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = [
        "name"
    ]
)
data class PokemonDetailsEntity(
    val name: String,
    val height: Int,
    val weight: Int,
    val attack: Int,
    val defense: Int,
    val health: Int,
    val type: String,
    val image: String?
)