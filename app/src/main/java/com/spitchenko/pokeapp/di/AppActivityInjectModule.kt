package com.spitchenko.pokeapp.di

import com.spitchenko.pokeapp.component.navigation.InjectingNavHostFragment
import com.spitchenko.pokeapp.feature.list.domain.di.PokemonListDomainModule
import com.spitchenko.pokeapp.feature.list.presentation.di.PokemonListPresentationModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AppActivityInjectModule {

    @ContributesAndroidInjector(
        modules = [
            FragmentBindingModule::class,
            PokemonListDomainModule::class,
            PokemonListPresentationModule::class
        ]
    )
    fun navHostFragmentInjector(): InjectingNavHostFragment
}