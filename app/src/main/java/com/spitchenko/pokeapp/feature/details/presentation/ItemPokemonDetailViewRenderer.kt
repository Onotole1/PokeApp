package com.spitchenko.pokeapp.feature.details.presentation

import androidx.viewbinding.ViewBinding
import com.spitchenko.pokeapp.component.extensions.setTextIfDifferent
import com.spitchenko.pokeapp.databinding.ItemPokemonDetailBinding
import com.spitchenko.pokeapp.feature.details.presentation.model.PokemonDetailUiModel
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingViewHolder
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewRenderer

object ItemPokemonDetailViewRenderer : ViewRenderer {

    override fun render(
        item: BindingClass,
        viewHolder: BindingViewHolder<ViewBinding>,
        position: Int
    ) {
        if (item !is PokemonDetailUiModel || viewHolder.binding !is ItemPokemonDetailBinding) {
            return
        }

        viewHolder.binding.pokemonDetails.setTextIfDifferent(item.text)
    }
}