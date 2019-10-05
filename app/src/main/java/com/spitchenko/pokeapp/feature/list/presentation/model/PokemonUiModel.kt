package com.spitchenko.pokeapp.feature.list.presentation.model

import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.BR
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.squareup.picasso.Picasso

data class PokemonUiModel(val pokemon: Pokemon, val picasso: Picasso): BindingClass {

    override val layoutId: Int = R.layout.item_pokemon
    override val itemId: Long by lazy(LazyThreadSafetyMode.NONE) {
        pokemon.image.hashCode().toLong() + pokemon.name.hashCode()
    }

    override fun areContentsTheSame(other: BindingClass): Boolean {
        if (other !is PokemonUiModel) {
            return false
        }

        return other.pokemon == pokemon
    }

    override fun bind(viewDataBinding: ViewDataBinding) {
        viewDataBinding.setVariable(BR.pokemon, pokemon)
        viewDataBinding.setVariable(BR.picasso, picasso)
    }
}