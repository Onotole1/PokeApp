package com.spitchenko.pokeapp.feature.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.extensions.getAsInstanceAt
import com.spitchenko.pokeapp.component.extensions.getViewModel
import com.spitchenko.pokeapp.component.extensions.showMessage
import com.spitchenko.pokeapp.databinding.ItemErrorBinding
import com.spitchenko.pokeapp.databinding.ItemPokemonBinding
import com.spitchenko.pokeapp.databinding.PokemonListFragmentBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingViewHolder
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonState
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonUiModel
import com.spitchenko.pokeapp.feature.list.presentation.model.toParcel
import javax.inject.Inject

class PokemonListFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewModel = getViewModel<PokemonListViewModel>(viewModelFactory)

        val context = requireContext()

        viewModel.messageEvent.observe(viewLifecycleOwner) {
            context.showMessage(it)
        }

        val binding = PokemonListFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        val pokemonsAdapter = BinderAdapter(mapOf(
            R.layout.item_error to { parent, _ ->
                BindingViewHolder<ItemErrorBinding>(parent, R.layout.item_error).also {
                    it.binding.itemErrorRetryButton.setOnClickListener {
                        viewModel.retry()
                    }
                }
            },
            R.layout.item_pokemon to { parent, adapter ->
                BindingViewHolder<ItemPokemonBinding>(parent, R.layout.item_pokemon).also {
                    it.binding.itemPokemonRetryButton.setOnClickListener { _ ->
                        val index = it.adapterPosition

                        viewModel.retryItem(index, adapter.itemList[index] as PokemonUiModel)
                    }
                    it.binding.itemPokemonErrorInfoButton.setOnClickListener { _ ->
                        val item: PokemonUiModel =
                            adapter.itemList.getAsInstanceAt(it.adapterPosition)

                        val message = (item.pokemonState as PokemonState.Error).message

                        requireContext().showMessage(message)
                    }
                    it.itemView.setOnClickListener { _ ->
                        val item: PokemonUiModel =
                            adapter.itemList.getAsInstanceAt(it.adapterPosition)

                        val details = (item.pokemonState as PokemonState.Data).details

                        val transitionName = it.binding.itemPokemonImage.transitionName

                        findNavController().navigate(
                            PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(
                                details.toParcel(),
                                transitionName
                            ),
                            FragmentNavigatorExtras(
                                it.binding.itemPokemonImage to transitionName
                            )
                        )
                    }
                }
            }
        ))

        binding.pokemonsList.apply {
            adapter = pokemonsAdapter

            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        binding.viewModel = viewModel

        return binding.root
    }
}