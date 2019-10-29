package com.spitchenko.pokeapp.feature.list.data.database

import com.spitchenko.pokeapp.feature.list.data.database.model.PokemonEntity
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon

fun PokemonEntity.toPokemon(): Pokemon = Pokemon(name)

fun Pokemon.toEntity(): PokemonEntity = PokemonEntity(name)