package com.spitchenko.pokeapp.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.spitchenko.pokeapp.feature.list.presentation.PokemonListFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(PokemonListFragment::class)
    abstract fun bindPokemonListFragment(mainFragment: PokemonListFragment): Fragment

    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}