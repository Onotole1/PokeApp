package com.spitchenko.pokeapp.feature.list.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.extensions.*
import com.spitchenko.pokeapp.databinding.PokemonListFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonListFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.pokemon_list_fragment), CoroutineScope by MainScope() {

    private lateinit var viewModel: PokemonListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel(viewModelFactory)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = requireContext()

        viewModel.messageEvent.observe(viewLifecycleOwner) {
            context.showMessage(it)
        }

        val binding = PokemonListFragmentBinding.bind(view)

        binding.pokemonsList.apply {
            adapter = createAdapter(viewModel)

            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )

            setOnScrolledToFooter(viewModel::showNextPage)
        }

        viewModel.messageEvent.observe(viewLifecycleOwner) {
            context.showMessage(it)
        }

        viewModel.uiModel.data.observe(viewLifecycleOwner) {
            launch {
                binding.pokemonsList.setBindingList(it)
            }
        }

        viewModel.uiModel.refreshProgressVisible.observe(viewLifecycleOwner) {
            binding.pokemonListFragmentSwipeRefresh.isRefreshing = it
        }

        binding.pokemonListFragmentSwipeRefresh.setOnRefreshListener(viewModel::refresh)

        viewModel.uiModel.emptyText.observe(viewLifecycleOwner, binding.emptyMessage::setMessage)

        viewModel.uiModel.emptyProgressVisible.observe(
            viewLifecycleOwner,
            binding.emptyProgress::setVisibleOrGone
        )

        viewModel.uiModel.dataVisible.observe(
            viewLifecycleOwner,
            binding.pokemonsList::setVisibleOrGone
        )

        viewModel.uiModel.emptyError.observe(viewLifecycleOwner, binding.errorMessage::setMessage)

        viewModel.uiModel.emptyErrorVisible.observe(viewLifecycleOwner) {
            binding.errorMessage.setVisibleOrGone(it)
            binding.retryButton.setVisibleOrGone(it)
        }

        viewModel.uiModel.emptyVisible.observe(
            viewLifecycleOwner,
            binding.emptyMessage::setVisibleOrGone
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()

        cancel()
    }
}