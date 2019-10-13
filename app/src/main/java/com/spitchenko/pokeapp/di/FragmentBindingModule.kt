package com.spitchenko.pokeapp.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.spitchenko.pokeapp.feature.details.presentation.PokemonDetailsFragment
import com.spitchenko.pokeapp.feature.list.presentation.PokemonListFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(PokemonListFragment::class)
    fun bindPokemonListFragment(fragment: PokemonListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(PokemonDetailsFragment::class)
    fun bindPokemonDetailsFragment(fragment: PokemonDetailsFragment): Fragment

    @Binds
    fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}