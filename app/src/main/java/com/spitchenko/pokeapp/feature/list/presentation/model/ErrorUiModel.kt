package com.spitchenko.pokeapp.feature.list.presentation.model

import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.BR
import com.spitchenko.pokeapp.component.binderadapter.BindingClass
import com.spitchenko.pokeapp.component.messaging.Message
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

private const val DEFAULT_ID = 9054434L

@Parcelize
data class ErrorUiModel(private val message: Message): BindingClass {

    @IgnoredOnParcel
    override val itemId: Long = DEFAULT_ID

    override fun bind(viewDataBinding: ViewDataBinding) {
        viewDataBinding.setVariable(BR.errorText, message)
    }
}