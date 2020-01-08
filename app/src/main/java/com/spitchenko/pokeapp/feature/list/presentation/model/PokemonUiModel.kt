package com.spitchenko.pokeapp.feature.list.presentation.model

import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.BR
import com.spitchenko.pokeapp.component.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonUiModel(val pokemon: PokemonDetailsParcel) : BindingClass {

    @IgnoredOnParcel
    override val itemId: Long by lazy(LazyThreadSafetyMode.NONE) {
        pokemon.details.image.hashCode().toLong() + pokemon.details.name.hashCode()
    }

    override fun bind(viewDataBinding: ViewDataBinding) {
        viewDataBinding.setVariable(BR.pokemonParcel, pokemon)
    }
}

fun Pokemon.toUiModel(): PokemonUiModel = PokemonUiModel(toParcel())