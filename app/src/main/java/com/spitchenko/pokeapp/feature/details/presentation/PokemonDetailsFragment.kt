package com.spitchenko.pokeapp.feature.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.transition.TransitionInflater
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.binderadapter.DefaultViewHolderFactory
import com.spitchenko.pokeapp.component.binderadapter.LayoutId
import com.spitchenko.pokeapp.component.binderadapter.binderAdapterOf
import com.spitchenko.pokeapp.component.extensions.doOnApplyWindowInsets
import com.spitchenko.pokeapp.component.extensions.getViewModel
import com.spitchenko.pokeapp.component.extensions.initNavigateUpClickListener
import com.spitchenko.pokeapp.component.lifecycle.ViewModelFactory
import com.spitchenko.pokeapp.databinding.PokemonDetailsFragmentBinding
import com.spitchenko.pokeapp.feature.details.presentation.model.PokemonDetailUiModel
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

        binding.pokemonDetailsList.adapter = binderAdapterOf(
            PokemonDetailUiModel::class to DefaultViewHolderFactory(LayoutId(R.layout.item_pokemon_detail))
        )

        binding.pokemonDetailsList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        binding.headerImage.transitionName = args.transitionName

        binding.homeButton.initNavigateUpClickListener()

        binding.root.systemUiVisibility =
            SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        binding.home.doOnApplyWindowInsets { windowInsets ->
            binding.motionLayout.getConstraintSet(R.id.expanded)
                ?.setMargin(R.id.home, ConstraintSet.TOP, windowInsets.systemWindowInsetTop)
            binding.motionLayout.getConstraintSet(R.id.collapsed)
                ?.setMargin(R.id.home, ConstraintSet.TOP, windowInsets.systemWindowInsetTop)
        }

        return binding.root
    }
}