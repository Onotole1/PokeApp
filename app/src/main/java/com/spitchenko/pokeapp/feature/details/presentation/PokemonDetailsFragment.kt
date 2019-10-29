package com.spitchenko.pokeapp.feature.details.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.transition.TransitionInflater
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.extensions.getViewModel
import com.spitchenko.pokeapp.component.extensions.initToolbar
import com.spitchenko.pokeapp.component.extensions.setBindingList
import com.spitchenko.pokeapp.component.extensions.setImageGlide
import com.spitchenko.pokeapp.component.lifecycle.ViewModelFactory
import com.spitchenko.pokeapp.databinding.PokemonDetailsFragmentBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

class PokemonDetailsFragment @Inject constructor(
    private val pokemonDetailsUiConverter: PokemonDetailsUiConverter,
    private val adapter: BinderAdapter
) : Fragment(R.layout.pokemon_details_fragment), CoroutineScope by MainScope() {

    private lateinit var viewModel: PokemonDetailsViewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = PokemonDetailsFragmentBinding.bind(view)

        binding.pokemonDetailsList.adapter = adapter

        binding.pokemonDetailsList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        binding.headerImage.transitionName = args.transitionName

        binding.toolbar.initToolbar(findNavController())

        binding.pokemonDetailsList.setBindingList(viewModel.uiModel.details)

        binding.headerImage.setImageGlide(
            args.pokemonDetails.details.image?.url,
            R.drawable.ic_pokeball_pokemon
        )

        binding.headerImage.transitionName = args.transitionName

        binding.toolbar.title = args.pokemonDetails.details.name
    }

    override fun onDestroyView() {
        super.onDestroyView()

        cancel()
    }
}