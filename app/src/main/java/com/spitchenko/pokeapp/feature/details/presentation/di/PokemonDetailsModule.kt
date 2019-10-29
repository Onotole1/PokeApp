package com.spitchenko.pokeapp.feature.details.presentation.di

import com.spitchenko.pokeapp.feature.details.presentation.ItemPokemonDetailViewHolderFactory
import com.spitchenko.pokeapp.feature.details.presentation.ItemPokemonDetailViewRenderer
import com.spitchenko.pokeapp.feature.details.presentation.PokemonDetailsUiConverter
import com.spitchenko.pokeapp.feature.details.presentation.PokemonDetailsUiConverterImpl
import com.spitchenko.pokeapp.feature.details.presentation.model.PokemonDetailUiModel
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class PokemonDetailsModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideAdapter(): BinderAdapter =
            BinderAdapter(
                factory = mapOf(
                    PokemonDetailUiModel.viewType to ItemPokemonDetailViewHolderFactory
                ),
                renders = mapOf(
                    PokemonDetailUiModel.viewType to ItemPokemonDetailViewRenderer
                )
            )
    }

    @Binds
    abstract fun bindPokemonDetailsUiConverter(impl: PokemonDetailsUiConverterImpl): PokemonDetailsUiConverter
}