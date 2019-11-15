package com.spitchenko.pokeapp.feature.list.data.database

import com.spitchenko.pokeapp.feature.list.data.database.model.PokemonEntity
import com.spitchenko.pokeapp.feature.list.domain.model.*

fun PokemonEntity.toPokemon(): Pokemon = Pokemon(
    name = name,
    height = Centimeters(height),
    weight = Kilograms(weight),
    attack = Attack(attack),
    defense = Defense(defense),
    health = Health(health),
    type = Type(type),
    image = image?.let {
        PokemonPicture(it)
    }
)

fun Pokemon.toEntity(): PokemonEntity = PokemonEntity(
    name = name,
    height = height.value,
    weight = weight.value,
    attack = attack.value,
    defense = defense.value,
    health = health.value,
    type = type.value,
    image = image?.url
)