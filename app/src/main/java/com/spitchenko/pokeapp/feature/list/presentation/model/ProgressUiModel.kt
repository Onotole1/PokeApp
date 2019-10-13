package com.spitchenko.pokeapp.feature.list.presentation.model

import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass

private const val DEFAULT_ID = 3490343L

class ProgressUiModel(
    override val itemId: Long = DEFAULT_ID
): BindingClass {

    override val layoutId: Int = R.layout.item_progress

    override fun areContentsTheSame(other: BindingClass): Boolean = other is ProgressUiModel

    override fun bind(viewDataBinding: ViewDataBinding, position: Int) = Unit
}