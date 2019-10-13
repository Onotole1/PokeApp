package com.spitchenko.pokeapp.feature.list.domain.model

data class PokemonDetails(
    val name: String,
    val height: Centimeters,
    val weight: Kilograms,
    val attack: Attack,
    val defense: Defense,
    val health: Health,
    val type: Type,
    val image: PokemonPicture?
)