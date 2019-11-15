package com.spitchenko.pokeapp.feature.list.presentation

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.binderadapter.BindingViewHolder
import com.spitchenko.pokeapp.component.binderadapter.LayoutId
import com.spitchenko.pokeapp.component.binderadapter.ViewHolderFactory
import com.spitchenko.pokeapp.databinding.ItemPokemonBinding
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import com.spitchenko.pokeapp.feature.list.presentation.model.toParcel

class ItemPokemonViewHolderFactory(
    private val navController: NavController
) : ViewHolderFactory {

    override fun create(
        parent: ViewGroup
    ): BindingViewHolder<ViewDataBinding> =
        BindingViewHolder<ItemPokemonBinding>(parent, LayoutId(R.layout.item_pokemon)).apply {
            itemView.setOnClickListener {
                val pokemon: Pokemon = requireNotNull(binding.pokemon)

                val transitionName = pokemon.name

                navController.navigate(
                    PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(
                        pokemon.toParcel(),
                        transitionName
                    ),
                    FragmentNavigatorExtras(
                        binding.itemPokemonImage to transitionName
                    )
                )
            }
        }
}