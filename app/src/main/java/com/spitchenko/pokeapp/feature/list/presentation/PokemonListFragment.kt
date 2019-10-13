package com.spitchenko.pokeapp.feature.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.extensions.getViewModel
import com.spitchenko.pokeapp.component.extensions.showMessage
import com.spitchenko.pokeapp.databinding.PokemonListFragmentBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingViewHolder
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonState
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonUiModel
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
                BindingViewHolder(parent, R.layout.item_error).also {
                    it.itemView.findViewById<Button>(
                        R.id.item_error_retry_button
                    ).setOnClickListener {
                        viewModel.retry()
                    }
                }
            },
            R.layout.item_pokemon to { parent, adapter ->
                BindingViewHolder(parent, R.layout.item_pokemon).also {
                    it.itemView.findViewById<Button>(
                        R.id.item_pokemon_retry_button
                    ).setOnClickListener { _ ->
                        val index = it.adapterPosition

                        viewModel.retryItem(index, adapter.itemList[index] as PokemonUiModel)
                    }
                    it.itemView.findViewById<ImageButton>(
                        R.id.item_pokemon_error_info_button
                    ).setOnClickListener { _ ->
                        val item = adapter.itemList[it.adapterPosition] as PokemonUiModel

                        val message = (item.pokemonState as PokemonState.Error).message

                        requireContext().showMessage(message)
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