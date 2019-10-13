package com.spitchenko.pokeapp.feature.list.domain.usecase

import com.spitchenko.pokeapp.feature.list.domain.model.PokemonDetails

interface PokemonDetailsRepository {

    suspend fun getDetailsByName(name: String): PokemonDetails
}