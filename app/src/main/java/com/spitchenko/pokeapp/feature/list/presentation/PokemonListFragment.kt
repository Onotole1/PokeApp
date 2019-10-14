package com.spitchenko.pokeapp.feature.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.extensions.getViewModel
import com.spitchenko.pokeapp.component.extensions.showMessage
import com.spitchenko.pokeapp.databinding.PokemonListFragmentBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.LayoutId
import javax.inject.Inject

class PokemonListFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private lateinit var viewModel: PokemonListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()

        viewModel.messageEvent.observe(viewLifecycleOwner) {
            context.showMessage(it)
        }

        val binding = PokemonListFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        val pokemonsAdapter = BinderAdapter(
            LayoutId(R.layout.item_error) to ItemErrorViewHolderFactory(viewModel),
            LayoutId(R.layout.item_pokemon) to ItemPokemonViewHolderFactory(
                context,
                findNavController(),
                viewModel
            )
        )

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