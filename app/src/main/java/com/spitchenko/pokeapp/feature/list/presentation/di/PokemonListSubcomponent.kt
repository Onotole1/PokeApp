package com.spitchenko.pokeapp.feature.list.presentation.di

import androidx.fragment.app.Fragment
import com.spitchenko.pokeapp.component.di.FragmentComponent
import com.spitchenko.pokeapp.component.di.FragmentScope
import com.spitchenko.pokeapp.feature.list.presentation.PokemonListFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent

@Subcomponent(
    modules = [
        PokemonListSubcomponent.Provision::class
    ]
)
@FragmentScope
interface PokemonListSubcomponent : FragmentComponent<PokemonListFragment> {

    override fun fragment(): PokemonListFragment

    @Module
    interface Provision {

        @Binds
        fun bindPokemonListFragment(impl: PokemonListFragment): Fragment
    }

    @Subcomponent.Factory
    interface Factory : FragmentComponent.Factory<PokemonListSubcomponent>
}