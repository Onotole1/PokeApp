package com.spitchenko.pokeapp.feature.list.presentation.di

import androidx.lifecycle.ViewModelProvider
import com.spitchenko.pokeapp.component.lifecycle.ViewModelFactory
import com.spitchenko.pokeapp.feature.list.domain.usecase.GetPokemonDetailsUseCase
import com.spitchenko.pokeapp.feature.list.domain.usecase.GetPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.domain.usecase.RefreshPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.presentation.PokemonListViewModel
import com.spitchenko.pokeapp.feature.list.presentation.paging.PAGE_SIZE
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
class PokemonListPresentationModule {

    @Provides
    fun providePokemonListViewModelFactory(
        getPokemonsUseCase: GetPokemonsUseCase,
        refreshPokemonsUseCase: RefreshPokemonsUseCase,
        pokemonDetailsUseCase: GetPokemonDetailsUseCase
    ): ViewModelProvider.Factory {
        val viewModelJob = SupervisorJob()
        val coroutineContext = Dispatchers.Main + viewModelJob

        return ViewModelFactory {
            PokemonListViewModel(
                getPokemonsUseCase,
                refreshPokemonsUseCase,
                pokemonDetailsUseCase,
                coroutineContext,
                PAGE_SIZE,
                viewModelJob
            ).also {
                it.showNextPage()
            }
        }
    }
}