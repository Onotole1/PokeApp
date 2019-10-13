package com.spitchenko.pokeapp.feature.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.transition.TransitionInflater
import com.spitchenko.pokeapp.component.extensions.getViewModel
import com.spitchenko.pokeapp.component.extensions.initToolbar
import com.spitchenko.pokeapp.component.lifecycle.ViewModelFactory
import com.spitchenko.pokeapp.databinding.PokemonDetailsFragmentBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import javax.inject.Inject

class PokemonDetailsFragment @Inject constructor(
    private val pokemonDetailsUiConverter: PokemonDetailsUiConverter
) : Fragment() {

    lateinit var viewModel: PokemonDetailsViewModel

    lateinit var args: PokemonDetailsFragmentArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        args = PokemonDetailsFragmentArgs.fromBundle(requireArguments())

        viewModel = getViewModel(ViewModelFactory {

            val pokemonDetails = args.pokemonDetails.details

            PokemonDetailsViewModel(pokemonDetails, pokemonDetailsUiConverter)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PokemonDetailsFragmentBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel

        binding.pokemonDetailsList.adapter = BinderAdapter()

        binding.pokemonDetailsList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        binding.headerImage.transitionName = args.transitionName

        binding.toolbar.initToolbar(findNavController())

        return binding.root
    }
}