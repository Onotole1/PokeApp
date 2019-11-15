package com.spitchenko.pokeapp.feature.list.presentation.model

import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.BR
import com.spitchenko.pokeapp.component.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon

data class PokemonUiModel(val pokemon: Pokemon) : BindingClass {

    override val itemId: Long by lazy(LazyThreadSafetyMode.NONE) {
        pokemon.image.hashCode().toLong() + pokemon.name.hashCode()
    }

    override fun areContentsTheSame(other: BindingClass): Boolean {
        if (other !is PokemonUiModel) {
            return false
        }

        return other.pokemon == pokemon
    }

    override fun bind(viewDataBinding: ViewDataBinding, position: Int) {
        viewDataBinding.setVariable(BR.pokemon, pokemon)
    }
}

fun Pokemon.toUiModel(): PokemonUiModel = PokemonUiModel(this)