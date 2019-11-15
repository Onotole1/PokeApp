package com.spitchenko.pokeapp.feature.list.presentation

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.binderadapter.BindingViewHolder
import com.spitchenko.pokeapp.component.binderadapter.LayoutId
import com.spitchenko.pokeapp.component.binderadapter.ViewHolderFactory
import com.spitchenko.pokeapp.databinding.ItemErrorBinding

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