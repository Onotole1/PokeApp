package com.spitchenko.pokeapp.di

import androidx.fragment.app.FragmentFactory
import com.spitchenko.pokeapp.component.di.FragmentComponent
import com.spitchenko.pokeapp.feature.details.presentation.PokemonDetailsFragment
import com.spitchenko.pokeapp.feature.details.presentation.di.PokemonDetailsSubcomponent
import com.spitchenko.pokeapp.feature.list.presentation.PokemonListFragment
import com.spitchenko.pokeapp.feature.list.presentation.di.PokemonListSubcomponent
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(PokemonListFragment::class)
    fun bindPokemonListFragment(impl: PokemonListSubcomponent.Factory): FragmentComponent.Factory<*>

    @Binds
    @IntoMap
    @FragmentKey(PokemonDetailsFragment::class)
    fun bindPokemonDetailsFragment(impl: PokemonDetailsSubcomponent.Factory): FragmentComponent.Factory<*>

    @Binds
    fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}