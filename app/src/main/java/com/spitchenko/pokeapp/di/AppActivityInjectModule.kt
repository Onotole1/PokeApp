package com.spitchenko.pokeapp.di

import com.spitchenko.pokeapp.component.di.InjectFragmentScope
import com.spitchenko.pokeapp.component.navigation.InjectingNavHostFragment
import com.spitchenko.pokeapp.feature.details.presentation.di.PokemonDetailsModule
import com.spitchenko.pokeapp.feature.list.presentation.di.PokemonListPresentationModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AppActivityInjectModule {

    @InjectFragmentScope
    @ContributesAndroidInjector(
        modules = [
            PokemonDetailsModule::class,
            FragmentBindingModule::class,
            PokemonListPresentationModule::class
        ]
    )
    fun navHostFragmentInjector(): InjectingNavHostFragment
}