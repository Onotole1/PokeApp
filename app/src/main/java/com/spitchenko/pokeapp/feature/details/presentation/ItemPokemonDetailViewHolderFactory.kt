package com.spitchenko.pokeapp.feature.details.presentation

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.spitchenko.pokeapp.component.extensions.layoutInflater
import com.spitchenko.pokeapp.databinding.ItemPokemonDetailBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingViewHolder
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewHolderFactory

object ItemPokemonDetailViewHolderFactory : ViewHolderFactory {

    override fun create(
        parent: ViewGroup,
        adapter: BinderAdapter
    ): BindingViewHolder<ViewBinding> {
        val binding = ItemPokemonDetailBinding.inflate(parent.context.layoutInflater, parent, false)

        return BindingViewHolder(binding)
    }
}