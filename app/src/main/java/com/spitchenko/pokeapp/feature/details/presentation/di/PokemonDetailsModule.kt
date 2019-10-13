package com.spitchenko.pokeapp.feature.details.presentation.di

import com.spitchenko.pokeapp.feature.details.presentation.PokemonDetailsUiConverter
import com.spitchenko.pokeapp.feature.details.presentation.PokemonDetailsUiConverterImpl
import dagger.Binds
import dagger.Module

@Module
interface PokemonDetailsModule {

    @Binds
    fun bindPokemonDetailsUiConverter(impl: PokemonDetailsUiConverterImpl): PokemonDetailsUiConverter
}