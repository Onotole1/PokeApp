package com.spitchenko.pokeapp.feature.list.presentation

import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.viewbinding.ViewBinding
import com.spitchenko.pokeapp.component.extensions.getAsInstanceAt
import com.spitchenko.pokeapp.component.extensions.layoutInflater
import com.spitchenko.pokeapp.component.extensions.showMessage
import com.spitchenko.pokeapp.databinding.ItemPokemonBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingViewHolder
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewHolderFactory
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonState
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonUiModel
import com.spitchenko.pokeapp.feature.list.presentation.model.toParcel

class ItemPokemonViewHolderFactory(
    private val navController: NavController,
    private val viewModel: PokemonListViewModel
) : ViewHolderFactory {

    override fun create(
        parent: ViewGroup,
        adapter: BinderAdapter
    ): BindingViewHolder<ViewBinding> {
        val binding = ItemPokemonBinding.inflate(parent.context.layoutInflater, parent, false)

        return BindingViewHolder(binding).apply {
            binding.itemPokemonRetryButton.setOnClickListener {
                val index = adapterPosition

                viewModel.retryItem(index, adapter.itemList[index] as PokemonUiModel)
            }
            binding.itemPokemonErrorInfoButton.setOnClickListener {
                val item: PokemonUiModel =
                    adapter.itemList.getAsInstanceAt(adapterPosition)

                val message = (item.pokemonState as PokemonState.Error).message

                parent.context.showMessage(message)
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
}