package com.spitchenko.pokeapp.feature.list.domain.usecase

import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import javax.inject.Inject

interface RefreshPokemonsUseCase {

    suspend operator fun invoke(pageSize: Int): List<Pokemon>
}

class RefreshPokemonsUseCaseImpl @Inject constructor(
    private val repository: PokemonsRepository
) : RefreshPokemonsUseCase {

    override suspend fun invoke(pageSize: Int): List<Pokemon> =
        repository.refreshAndGetFirstPage(pageSize)
}