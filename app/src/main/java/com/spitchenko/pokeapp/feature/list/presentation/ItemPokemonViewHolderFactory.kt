package com.spitchenko.pokeapp.feature.list.presentation

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.spitchenko.pokeapp.component.extensions.getAsInstanceAt
import com.spitchenko.pokeapp.component.extensions.showMessage
import com.spitchenko.pokeapp.databinding.ItemPokemonBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingViewHolder
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.LayoutId
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewHolderFactory
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonState
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonUiModel
import com.spitchenko.pokeapp.feature.list.presentation.model.toParcel

class ItemPokemonViewHolderFactory(
    private val context: Context,
    private val navController: NavController,
    private val viewModel: PokemonListViewModel
) : ViewHolderFactory {

    override fun create(
        parent: ViewGroup,
        layoutId: LayoutId,
        adapter: BinderAdapter
    ): BindingViewHolder<ViewDataBinding> =
        BindingViewHolder<ItemPokemonBinding>(parent, layoutId).apply {
            binding.itemPokemonRetryButton.setOnClickListener {
                val index = adapterPosition

                viewModel.retryItem(index, adapter.itemList[index] as PokemonUiModel)
            }
            binding.itemPokemonErrorInfoButton.setOnClickListener {
                val item: PokemonUiModel =
                    adapter.itemList.getAsInstanceAt(adapterPosition)

                val message = (item.pokemonState as PokemonState.Error).message

                context.showMessage(message)
            }
            itemView.setOnClickListener {
                val item: PokemonUiModel =
                    adapter.itemList.getAsInstanceAt(adapterPosition)

                val details = (item.pokemonState as PokemonState.Data).details

                val transitionName = binding.itemPokemonImage.transitionName

                navController.navigate(
                    PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(
                        details.toParcel(),
                        transitionName
                    ),
                    FragmentNavigatorExtras(
                        binding.itemPokemonImage to transitionName
                    )
                )
            }
        }
}