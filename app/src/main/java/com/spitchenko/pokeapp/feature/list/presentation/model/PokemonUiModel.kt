package com.spitchenko.pokeapp.feature.list.presentation.model

import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.BR
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass

data class PokemonUiModel(val pokemonState: PokemonState): BindingClass {

    override val layoutId: Int = R.layout.item_pokemon
    override val itemId: Long by lazy(LazyThreadSafetyMode.NONE) {
        pokemonState.name.hashCode().toLong()
    }

    override fun areContentsTheSame(other: BindingClass): Boolean {
        if (other !is PokemonUiModel) {
            return false
        }

        return other.pokemonState == pokemonState
    }

    override fun bind(viewDataBinding: ViewDataBinding) {
        viewDataBinding.setVariable(BR.name, pokemonState.name)
        when (pokemonState) {
            is PokemonState.Data -> {
                viewDataBinding.setVariable(BR.success, true)
                viewDataBinding.setVariable(BR.progress, false)
                viewDataBinding.setVariable(BR.error, false)
                viewDataBinding.setVariable(BR.imageUrl, pokemonState.details.image?.url)
            }

            is PokemonState.Progress -> {
                viewDataBinding.setVariable(BR.success, false)
                viewDataBinding.setVariable(BR.progress, true)
                viewDataBinding.setVariable(BR.error, false)
            }

            is PokemonState.Error -> {
                viewDataBinding.setVariable(BR.success, false)
                viewDataBinding.setVariable(BR.progress, false)
                viewDataBinding.setVariable(BR.error, true)
                viewDataBinding.setVariable(BR.errorMessage, pokemonState.message)
            }
        }
    }
}