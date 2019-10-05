package com.spitchenko.pokeapp.feature.list.presentation

import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.paging.ListViewModel
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingViewHolder

class PokemonsAdapter(
    private val viewModel: ListViewModel<BindingClass>
) : BinderAdapter<ViewDataBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<ViewDataBinding> {
        return super.onCreateViewHolder(parent, viewType).also { binding ->

            if (viewType == R.layout.item_error) {
                binding.itemView.findViewById<Button>(
                    R.id.item_error_retry_button
                ).setOnClickListener {
                    viewModel.retry()
                }
            }
        }
    }
}