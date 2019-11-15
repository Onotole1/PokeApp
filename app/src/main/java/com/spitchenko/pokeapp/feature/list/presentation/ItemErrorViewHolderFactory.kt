package com.spitchenko.pokeapp.feature.list.presentation

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.databinding.ItemErrorBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingViewHolder
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.LayoutId
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewHolderFactory

class ItemErrorViewHolderFactory(private val viewModel: PokemonListViewModel) : ViewHolderFactory {

    override fun create(
        parent: ViewGroup
    ): BindingViewHolder<ViewDataBinding> =
        BindingViewHolder<ItemErrorBinding>(parent, LayoutId(R.layout.item_error)).apply {
            binding.itemErrorRetryButton.setOnClickListener {
                viewModel.retry()
            }
        }
}