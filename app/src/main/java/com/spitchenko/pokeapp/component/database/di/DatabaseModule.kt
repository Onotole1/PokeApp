package com.spitchenko.pokeapp.component.database.di

import android.content.Context
import androidx.room.Room
import com.spitchenko.pokeapp.component.database.DATABASE_NAME
import com.spitchenko.pokeapp.component.database.Database
import com.spitchenko.pokeapp.component.di.ApplicationScope
import com.spitchenko.pokeapp.feature.list.data.database.dao.PokemonDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @ApplicationScope
    @Provides
    fun provideDatabase(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @ApplicationScope
    @Provides
    fun providePokemonDao(database: Database): PokemonDao = database.getPokemonDao()
}