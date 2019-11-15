package com.spitchenko.pokeapp.feature.list.presentation.model

import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass

private const val DEFAULT_ID = 3490343L

object ProgressUiModel : BindingClass {

    override val itemId: Long = DEFAULT_ID

    override fun areContentsTheSame(other: BindingClass): Boolean = other is ProgressUiModel

    override fun bind(viewDataBinding: ViewDataBinding, position: Int) = Unit
}