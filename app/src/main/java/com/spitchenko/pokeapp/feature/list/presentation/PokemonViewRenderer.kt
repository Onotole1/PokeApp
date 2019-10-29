package com.spitchenko.pokeapp.feature.list.presentation

import androidx.viewbinding.ViewBinding
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.extensions.*
import com.spitchenko.pokeapp.databinding.ItemPokemonBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingViewHolder
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewRenderer
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonState
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonUiModel

private const val TRANSITION_NAME = "PokemonViewRendererTransition"

object PokemonViewRenderer : ViewRenderer {

    override fun render(
        item: BindingClass,
        viewHolder: BindingViewHolder<ViewBinding>,
        position: Int
    ) {
        if (item !is PokemonUiModel || viewHolder.binding !is ItemPokemonBinding) {
            return
        }

        val binding: ItemPokemonBinding = viewHolder.binding

        binding.itemPokemonName.setTextIfDifferent(item.pokemonState.name)
        binding.itemPokemonImage.transitionName = TRANSITION_NAME + position
        when (val pokemonState = item.pokemonState) {
            is PokemonState.Data -> {
                binding.root.setClickableAndFocusable(true)
                binding.itemPokemonErrorGroup.setVisibleOrGone(false)
                binding.itemPokemonName.setDrawables(
                    drawableEnd = viewHolder.itemView.context.getDrawable(R.drawable.ic_chevron_right)
                )
                binding.itemPokemonProgress.setVisibleOrGone(false)
                binding.itemPokemonImage.setImageGlide(
                    pokemonState.details.image?.url,
                    R.drawable.ic_pokeball_pokemon
                )
            }

            is PokemonState.Progress -> {
                binding.root.setClickableAndFocusable(false)
                binding.itemPokemonName.setDrawables()
                binding.itemPokemonErrorGroup.setVisibleOrGone(false)
                binding.itemPokemonProgress.setVisibleOrGone(true)
                binding.itemPokemonImage.setImageGlide(null, R.drawable.ic_pokeball_pokemon)
            }

            is PokemonState.Error -> {
                binding.root.setClickableAndFocusable(false)
                binding.itemPokemonName.setDrawables()
                binding.itemPokemonErrorGroup.setVisibleOrGone(true)
                binding.itemPokemonProgress.setVisibleOrGone(false)
                binding.itemPokemonImage.setImageGlide(null, R.drawable.ic_pokeball_pokemon)
            }
        }
    }
}