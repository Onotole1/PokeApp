package com.spitchenko.pokeapp.feature.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.spitchenko.pokeapp.component.extensions.getViewModel
import com.spitchenko.pokeapp.databinding.ItemPokemonBinding
import com.spitchenko.pokeapp.databinding.PokemonListFragmentBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
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

        val binding = PokemonListFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        val binderAdapter = BinderAdapter<ItemPokemonBinding>()

        binding.pokemonsList.apply {
            adapter = binderAdapter

            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        binding.viewModel = viewModel

        return binding.root
    }
}