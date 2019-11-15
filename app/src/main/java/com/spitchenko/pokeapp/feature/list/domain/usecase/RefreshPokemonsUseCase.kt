package com.spitchenko.pokeapp.feature.list.domain.usecase

import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import javax.inject.Inject

class RefreshPokemonsUseCase @Inject constructor(
    private val repository: PokemonsRepository
) {

    suspend operator fun invoke(pageSize: Int): List<Pokemon> =
        repository.refreshAndGetFirstPage(pageSize)
}