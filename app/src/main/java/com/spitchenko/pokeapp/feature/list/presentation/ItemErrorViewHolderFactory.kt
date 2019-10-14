package com.spitchenko.pokeapp.feature.list.presentation

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.databinding.ItemErrorBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingViewHolder
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.LayoutId
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewHolderFactory

class ItemErrorViewHolderFactory(private val viewModel: PokemonListViewModel) : ViewHolderFactory {

    override fun create(
        parent: ViewGroup,
        layoutId: LayoutId,
        adapter: BinderAdapter
    ): BindingViewHolder<ViewDataBinding> =
        BindingViewHolder<ItemErrorBinding>(parent, layoutId).apply {
            binding.itemErrorRetryButton.setOnClickListener {
                viewModel.retry()
            }
        }
}