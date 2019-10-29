package com.spitchenko.pokeapp.feature.list.data.di

import com.spitchenko.pokeapp.component.di.ApplicationScope
import com.spitchenko.pokeapp.feature.list.data.database.datasource.PokemonDetailsLocalDataSource
import com.spitchenko.pokeapp.feature.list.data.database.datasource.PokemonDetailsLocalDataSourceImpl
import com.spitchenko.pokeapp.feature.list.data.database.datasource.PokemonLocalDataSource
import com.spitchenko.pokeapp.feature.list.data.database.datasource.PokemonLocalDataSourceImpl
import com.spitchenko.pokeapp.feature.list.data.repository.PokemonsRepositoryImpl
import com.spitchenko.pokeapp.feature.list.data.network.*
import com.spitchenko.pokeapp.feature.list.data.network.api.PokemonDetailsApi
import com.spitchenko.pokeapp.feature.list.data.network.api.PokemonListApi
import com.spitchenko.pokeapp.feature.list.data.network.datasource.PokemonDetailsNetworkDataSource
import com.spitchenko.pokeapp.feature.list.data.network.datasource.PokemonDetailsNetworkDataSourceImpl
import com.spitchenko.pokeapp.feature.list.data.network.datasource.PokemonsNetworkDataSource
import com.spitchenko.pokeapp.feature.list.data.network.datasource.PokemonsNetworkDataSourceImpl
import com.spitchenko.pokeapp.feature.list.data.repository.PokemonDetailsRepositoryImpl
import com.spitchenko.pokeapp.feature.list.domain.usecase.PokemonDetailsRepository
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

        @Provides
        @ApplicationScope
        @JvmStatic
        fun providePokemonDetailsApi(retrofit: Retrofit): PokemonDetailsApi = retrofit.create()
    }

    @Binds
    abstract fun bindPokemonsNetworkConverter(impl: PokemonsNetworkConverterImpl): PokemonsNetworkConverter

    @ApplicationScope
    @Binds
    abstract fun bindPokemonsNetworkDataSource(impl: PokemonsNetworkDataSourceImpl): PokemonsNetworkDataSource

    @ApplicationScope
    @Binds
    abstract fun bindPokemonsRepository(impl: PokemonsRepositoryImpl): PokemonsRepository

    @Binds
    abstract fun bindPokemonsPictureConverter(impl: PokemonPictureConverterImpl): PokemonPictureConverter

    @Binds
    abstract fun bindPokemonTypeNetworkConverter(impl: PokemonTypeNetworkConverterImpl): PokemonTypeNetworkConverter

    @Binds
    abstract fun bindPokemonDetailsNetworkConverter(impl: PokemonDetailsNetworkConverterImpl): PokemonDetailsNetworkConverter

    @ApplicationScope
    @Binds
    abstract fun bindPokemonDetailsNetworkDataSource(impl: PokemonDetailsNetworkDataSourceImpl): PokemonDetailsNetworkDataSource

    @ApplicationScope
    @Binds
    abstract fun bindPokemonsDetailsRepository(impl: PokemonDetailsRepositoryImpl): PokemonDetailsRepository

    @ApplicationScope
    @Binds
    abstract fun bindPokemonDetailsLocalDataSource(impl: PokemonDetailsLocalDataSourceImpl): PokemonDetailsLocalDataSource

    @ApplicationScope
    @Binds
    abstract fun bindPokemonLocalDataSource(impl: PokemonLocalDataSourceImpl): PokemonLocalDataSource
}