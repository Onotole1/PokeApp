package com.spitchenko.pokeapp.feature.details.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import com.spitchenko.pokeapp.component.di.Holder
import com.spitchenko.pokeapp.component.di.InjectFragmentScope
import com.spitchenko.pokeapp.component.lifecycle.ViewModelCreator
import com.spitchenko.pokeapp.component.lifecycle.annotations.ViewModelKey
import com.spitchenko.pokeapp.feature.details.presentation.PokemonDetailsFragmentArgs
import com.spitchenko.pokeapp.feature.details.presentation.PokemonDetailsUiConverter
import com.spitchenko.pokeapp.feature.details.presentation.PokemonDetailsUiConverterImpl
import com.spitchenko.pokeapp.feature.details.presentation.PokemonDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(subcomponents = [PokemonDetailsSubcomponent::class])
abstract class PokemonDetailsModule {

    @Module
    companion object {

        @ViewModelKey(PokemonDetailsViewModel::class)
        @IntoMap
        @Provides
        @JvmStatic
        fun providePokemonListViewModelFactory(
            pokemonDetailsUiConverter: PokemonDetailsUiConverter,
            holder: Holder<Fragment>
        ): ViewModelCreator =
            object : ViewModelCreator {

                override fun create(handle: SavedStateHandle): PokemonDetailsViewModel =
                    PokemonDetailsViewModel(
                        handle,
                        PokemonDetailsFragmentArgs.fromBundle(
                            holder.reference.requireArguments()
                        ).pokemonDetails.details,
                        pokemonDetailsUiConverter
                    )
            }

        @InjectFragmentScope
        @Provides
        @JvmStatic
        fun holder(): Holder<Fragment> = Holder()
    }

    @Binds
    abstract fun bindPokemonDetailsUiConverter(impl: PokemonDetailsUiConverterImpl): PokemonDetailsUiConverter
}