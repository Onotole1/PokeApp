package com.spitchenko.pokeapp.feature.details.presentation.model

import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.BR
import com.spitchenko.pokeapp.component.binderadapter.BindingClass
import com.spitchenko.pokeapp.component.messaging.Message
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonDetailUiModel(val text: Message) : BindingClass {

    @IgnoredOnParcel
    override val itemId: Long = text.hashCode().toLong()

    override fun bind(viewDataBinding: ViewDataBinding) {
        viewDataBinding.setVariable(BR.text, text)
    }
}