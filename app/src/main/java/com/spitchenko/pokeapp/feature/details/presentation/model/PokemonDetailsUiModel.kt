package com.spitchenko.pokeapp.feature.details.presentation.model

data class PokemonDetailsUiModel(
    val name: String,
    val imageUrl: String?,
    val details: List<PokemonDetailUiModel>
)