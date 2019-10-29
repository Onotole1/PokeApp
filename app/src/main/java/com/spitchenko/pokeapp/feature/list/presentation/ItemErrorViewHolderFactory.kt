package com.spitchenko.pokeapp.feature.list.presentation

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.spitchenko.pokeapp.component.extensions.layoutInflater
import com.spitchenko.pokeapp.databinding.ItemErrorBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingViewHolder
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewHolderFactory

class ItemErrorViewHolderFactory(private val viewModel: PokemonListViewModel) : ViewHolderFactory {

    override fun create(
        parent: ViewGroup,
        adapter: BinderAdapter
    ): BindingViewHolder<ViewBinding> =
        BindingViewHolder<ItemErrorBinding>(ItemErrorBinding.inflate(parent.context.layoutInflater, parent, false)).apply {
            binding.itemErrorRetryButton.setOnClickListener {
                viewModel.retry()
            }
        }
}