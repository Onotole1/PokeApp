package com.spitchenko.pokeapp.feature.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.spitchenko.pokeapp.component.extensions.getViewModel
import com.spitchenko.pokeapp.component.extensions.showMessage
import com.spitchenko.pokeapp.databinding.PokemonListFragmentBinding
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

        val pokemonsAdapter = PokemonsAdapter(viewModel)

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