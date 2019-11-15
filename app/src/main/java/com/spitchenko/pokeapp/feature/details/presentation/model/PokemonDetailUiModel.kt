package com.spitchenko.pokeapp.feature.details.presentation.model

import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.BR
import com.spitchenko.pokeapp.component.binderadapter.BindingClass
import com.spitchenko.pokeapp.component.messaging.Message

data class PokemonDetailUiModel(val text: Message) : BindingClass {

    override val itemId: Long = text.hashCode().toLong()

    override fun areContentsTheSame(other: BindingClass): Boolean =
        (other as? PokemonDetailUiModel)?.text == text

    override fun bind(viewDataBinding: ViewDataBinding, position: Int) {
        viewDataBinding.setVariable(BR.text, text)
    }
}