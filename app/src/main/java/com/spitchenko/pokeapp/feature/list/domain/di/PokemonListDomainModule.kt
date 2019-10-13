package com.spitchenko.pokeapp.feature.list.domain.di

import com.spitchenko.pokeapp.feature.list.domain.usecase.*
import dagger.Binds
import dagger.Module

@Module
interface PokemonListDomainModule {

    @Binds
    fun bindGetPokemonsUseCase(impl: GetPokemonsUseCaseImpl): GetPokemonsUseCase

    @Binds
    fun bindRefreshPokemonsUseCase(impl: RefreshPokemonsUseCaseImpl): RefreshPokemonsUseCase

    @Binds
    fun bindGetPokemonDetailsUseCase(impl: GetPokemonDetailsUseCaseImpl): GetPokemonDetailsUseCase
}