package com.spitchenko.pokeapp.feature.list.domain.usecase

import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import javax.inject.Inject

interface GetPokemonsUseCase {

    suspend operator fun invoke(pageSize: Int, offset: Int): List<Pokemon>
}

class GetPokemonsUseCaseImpl @Inject constructor(
    private val repository: PokemonsRepository
): GetPokemonsUseCase {

    override suspend fun invoke(pageSize: Int, offset: Int): List<Pokemon> =
        repository.getPokemons(pageSize, offset)
}