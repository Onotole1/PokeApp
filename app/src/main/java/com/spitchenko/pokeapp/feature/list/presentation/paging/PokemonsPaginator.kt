package com.spitchenko.pokeapp.feature.list.presentation.paging

import com.spitchenko.pokeapp.component.paging.Paginator
import com.spitchenko.pokeapp.feature.list.domain.usecase.GetPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.domain.usecase.RefreshPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.presentation.PokemonUiConverter
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonUiModel
import com.spitchenko.pokeapp.feature.list.presentation.model.ProgressUiModel

class PokemonsPaginator(
    private val refreshPokemonsUseCase: RefreshPokemonsUseCase,
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val pokemonUiConverter: PokemonUiConverter
): Paginator<BindingClass> {

    override val progressItem: BindingClass = ProgressUiModel()

    override suspend fun resetPagingAndGetFirstPage(): List<PokemonUiModel> =
        refreshPokemonsUseCase(PAGE_SIZE).map(pokemonUiConverter::convert)

    override suspend fun getNextPage(offset: Int): List<PokemonUiModel> =
        getPokemonsUseCase(PAGE_SIZE, offset).map(pokemonUiConverter::convert)
}