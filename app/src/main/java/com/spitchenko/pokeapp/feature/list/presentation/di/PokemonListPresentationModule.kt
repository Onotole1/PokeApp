package com.spitchenko.pokeapp.feature.list.presentation.di

import androidx.lifecycle.SavedStateHandle
import com.spitchenko.pokeapp.component.lifecycle.ViewModelCreator
import com.spitchenko.pokeapp.component.lifecycle.annotations.ViewModelKey
import com.spitchenko.pokeapp.feature.details.presentation.di.PokemonDetailsSubcomponent
import com.spitchenko.pokeapp.feature.list.domain.usecase.GetPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.domain.usecase.RefreshPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.presentation.PokemonListViewModel
import com.spitchenko.pokeapp.feature.list.presentation.paging.PAGE_SIZE
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module(subcomponents = [PokemonListSubcomponent::class])
class PokemonListPresentationModule {

    @ViewModelKey(PokemonListViewModel::class)
    @IntoMap
    @Provides
    fun providePokemonListViewModelFactory(
        getPokemonsUseCase: GetPokemonsUseCase,
        refreshPokemonsUseCase: RefreshPokemonsUseCase
    ): ViewModelCreator =
        object : ViewModelCreator {

            override fun create(handle: SavedStateHandle): PokemonListViewModel {
                val viewModelJob = SupervisorJob()
                val coroutineContext = Dispatchers.Main + viewModelJob

                return PokemonListViewModel(
                    getPokemonsUseCase,
                    refreshPokemonsUseCase,
                    coroutineContext,
                    PAGE_SIZE,
                    viewModelJob,
                    handle
                ).also(PokemonListViewModel::restoreState)
            }
        }
}