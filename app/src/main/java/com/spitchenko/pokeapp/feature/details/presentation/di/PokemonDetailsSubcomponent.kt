package com.spitchenko.pokeapp.feature.details.presentation.di

import androidx.fragment.app.Fragment
import com.spitchenko.pokeapp.component.di.FragmentComponent
import com.spitchenko.pokeapp.component.di.FragmentScope
import com.spitchenko.pokeapp.component.di.Holder
import com.spitchenko.pokeapp.component.lifecycle.ViewModelProviders
import com.spitchenko.pokeapp.feature.details.presentation.PokemonDetailsFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(
    modules = [
        PokemonDetailsSubcomponent.Provision::class
    ]
)
@FragmentScope
interface PokemonDetailsSubcomponent : FragmentComponent<PokemonDetailsFragment> {

    override fun fragment(): PokemonDetailsFragment

    @Module
    object Provision {

        @JvmStatic
        @Provides
        fun provideDetailsFragment(
            viewModelProviders: ViewModelProviders,
            holder: Holder<Fragment>
        ): PokemonDetailsFragment =
            PokemonDetailsFragment(viewModelProviders).also {
                holder.reference = it
            }
    }

    @Subcomponent.Factory
    interface Factory : FragmentComponent.Factory<PokemonDetailsSubcomponent>
}