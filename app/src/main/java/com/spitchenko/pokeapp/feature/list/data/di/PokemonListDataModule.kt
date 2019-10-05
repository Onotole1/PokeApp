package com.spitchenko.pokeapp.feature.list.data.di

import com.spitchenko.pokeapp.component.di.ApplicationScope
import com.spitchenko.pokeapp.feature.list.data.PokemonsRepositoryImpl
import com.spitchenko.pokeapp.feature.list.data.network.*
import com.spitchenko.pokeapp.feature.list.domain.usecase.PokemonsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
abstract class PokemonListDataModule {

    @Module
    companion object {

        @Provides
        @ApplicationScope
        @JvmStatic
        fun providePokemonListApi(retrofit: Retrofit): PokemonListApi = retrofit.create()
    }

    @Binds
    abstract fun bindPokemonsNetworkConverter(impl: PokemonsNetworkConverterImpl): PokemonsNetworkConverter

    @ApplicationScope
    @Binds
    abstract fun bindPokemonsNetworkDataSource(impl: PokemonsNetworkDataSourceImpl): PokemonsNetworkDataSource

    @ApplicationScope
    @Binds
    abstract fun bindPokemonsRepository(impl: PokemonsRepositoryImpl): PokemonsRepository
}