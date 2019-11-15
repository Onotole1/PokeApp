package com.spitchenko.pokeapp.feature.list.presentation.model

import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.BR
import com.spitchenko.pokeapp.component.binderadapter.BindingClass
import com.spitchenko.pokeapp.component.messaging.Message

private const val DEFAULT_ID = 9054434L

data class ErrorUiModel(private val message: Message): BindingClass {

    override val itemId: Long = DEFAULT_ID

    override fun areContentsTheSame(other: BindingClass): Boolean = other is ErrorUiModel

    override fun bind(viewDataBinding: ViewDataBinding, position: Int) {
        viewDataBinding.setVariable(BR.errorText, message)
    }
}