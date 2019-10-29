package com.spitchenko.pokeapp.feature.list.data.database

import com.spitchenko.pokeapp.feature.list.data.database.model.PokemonDetailsEntity
import com.spitchenko.pokeapp.feature.list.domain.model.*

fun PokemonDetailsEntity.toDetails(): PokemonDetails = PokemonDetails(
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

fun PokemonDetails.toEntity(): PokemonDetailsEntity = PokemonDetailsEntity(
    name = name,
    height = height.value,
    weight = weight.value,
    attack = attack.value,
    defense = defense.value,
    health = health.value,
    type = type.value,
    image = image?.url
)