package com.spitchenko.pokeapp.feature.list.domain.di

import com.spitchenko.pokeapp.feature.list.domain.usecase.GetPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.domain.usecase.GetPokemonsUseCaseImpl
import com.spitchenko.pokeapp.feature.list.domain.usecase.RefreshPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.domain.usecase.RefreshPokemonsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface PokemonListDomainModule {

    @Binds
    fun bindGetPokemonsUseCase(impl: GetPokemonsUseCaseImpl): GetPokemonsUseCase

    @Binds
    fun bindRefreshPokemonsUseCase(impl: RefreshPokemonsUseCaseImpl): RefreshPokemonsUseCase
}