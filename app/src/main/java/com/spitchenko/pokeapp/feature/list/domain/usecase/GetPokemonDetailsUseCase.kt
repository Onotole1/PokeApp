package com.spitchenko.pokeapp.feature.list.domain.usecase

import com.spitchenko.pokeapp.feature.list.domain.model.PokemonDetails
import javax.inject.Inject

interface GetPokemonDetailsUseCase {

    suspend operator fun invoke(name: String): PokemonDetails
}

class GetPokemonDetailsUseCaseImpl @Inject constructor(private val repository: PokemonDetailsRepository) :
    GetPokemonDetailsUseCase {

    override suspend operator fun invoke(name: String): PokemonDetails =
        repository.getDetailsByName(name)
}