package com.spitchenko.pokeapp.feature.list.presentation.di

import androidx.lifecycle.ViewModelProvider
import com.spitchenko.pokeapp.component.lifecycle.ViewModelFactory
import com.spitchenko.pokeapp.feature.list.domain.usecase.GetPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.domain.usecase.RefreshPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.presentation.PokemonListViewModel
import com.spitchenko.pokeapp.feature.list.presentation.PokemonUiConverterImpl
import com.spitchenko.pokeapp.component.paging.ListViewModelDelegate
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonUiModel
import com.spitchenko.pokeapp.feature.list.presentation.paging.PAGE_SIZE
import com.spitchenko.pokeapp.feature.list.presentation.paging.PokemonsPaginator
import com.squareup.picasso.Picasso
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
        picasso: Picasso
    ): ViewModelProvider.Factory {

        val paginator = PokemonsPaginator(
            refreshPokemonsUseCase,
            getPokemonsUseCase,
            PokemonUiConverterImpl(picasso)
        )

        val viewModelJob = SupervisorJob()
        val coroutineContext = Dispatchers.Main + viewModelJob

        val delegate = ListViewModelDelegate(
            coroutineContext = coroutineContext,
            pageSize = PAGE_SIZE,
            paginator = paginator
        )

        return ViewModelFactory {
            PokemonListViewModel(viewModelJob, delegate).also {
                it.showNextPage()
            }
        }
    }
}