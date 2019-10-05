package com.spitchenko.pokeapp.feature.list.presentation.model

import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.BR
import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.messaging.Message
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass

private const val DEFAULT_ID = 9054434L

data class ErrorUiModel(private val message: Message): BindingClass {

    override val itemId: Long = DEFAULT_ID

    override val layoutId: Int = R.layout.item_error

    override fun areContentsTheSame(other: BindingClass): Boolean = other is ErrorUiModel

    override fun bind(viewDataBinding: ViewDataBinding) {
        viewDataBinding.setVariable(BR.errorText, message)
    }
}