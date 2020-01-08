package com.spitchenko.pokeapp.feature.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.transition.TransitionInflater
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.binderadapter.DefaultViewHolderFactory
import com.spitchenko.pokeapp.component.binderadapter.LayoutId
import com.spitchenko.pokeapp.component.binderadapter.binderAdapterOf
import com.spitchenko.pokeapp.component.extensions.doOnApplyWindowInsets
import com.spitchenko.pokeapp.component.extensions.initNavigateUpClickListener
import com.spitchenko.pokeapp.component.lifecycle.ViewModelProviders
import com.spitchenko.pokeapp.component.extensions.setFullScreen
import com.spitchenko.pokeapp.component.lifecycle.extension.viewModels
import com.spitchenko.pokeapp.component.log.debug
import com.spitchenko.pokeapp.databinding.PokemonDetailsFragmentBinding
import com.spitchenko.pokeapp.feature.details.presentation.model.PokemonDetailUiModel

class PokemonDetailsFragment(
    viewModelProviders: ViewModelProviders
) : Fragment() {

    private val viewModel: PokemonDetailsViewModel by viewModels(viewModelProviders)

    private lateinit var args: PokemonDetailsFragmentArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        args = PokemonDetailsFragmentArgs.fromBundle(requireArguments())

        postponeEnterTransition()
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

        binding.root.setFullScreen()

        binding.home.doOnApplyWindowInsets { windowInsets ->
            debug("apply insets : $windowInsets")

            binding.home.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = windowInsets.stableInsetTop
            }
            binding.motionLayout.getConstraintSet(R.id.expanded)
                ?.setMargin(R.id.home, ConstraintSet.TOP, windowInsets.systemWindowInsetTop)
            binding.motionLayout.getConstraintSet(R.id.collapsed)
                ?.setMargin(R.id.home, ConstraintSet.TOP, windowInsets.systemWindowInsetTop)
            windowInsets.consumeSystemWindowInsets()

            startPostponedEnterTransition()
        }

        return binding.root
    }
}