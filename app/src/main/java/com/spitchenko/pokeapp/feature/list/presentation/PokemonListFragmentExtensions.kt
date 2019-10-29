package com.spitchenko.pokeapp.feature.list.presentation

import androidx.navigation.fragment.findNavController
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import com.spitchenko.pokeapp.feature.list.presentation.model.ErrorUiModel
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonUiModel
import com.spitchenko.pokeapp.feature.list.presentation.model.ProgressUiModel

fun PokemonListFragment.createAdapter(viewModel: PokemonListViewModel): BinderAdapter {
    val viewHolderFactory = mapOf(
        ErrorUiModel.viewType to ItemErrorViewHolderFactory(viewModel),
        PokemonUiModel.viewType to ItemPokemonViewHolderFactory(
            findNavController(),
            viewModel
        ),
        ProgressUiModel.viewType to ItemProgressViewHolderFactory
    )

    val viewRenders = mapOf(
        PokemonUiModel.viewType to PokemonViewRenderer,
        ErrorUiModel.viewType to ErrorViewRenderer
    )

    return BinderAdapter(
        viewHolderFactory,
        viewRenders
    )
}