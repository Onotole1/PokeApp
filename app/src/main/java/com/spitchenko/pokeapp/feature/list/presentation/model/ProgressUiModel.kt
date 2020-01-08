package com.spitchenko.pokeapp.feature.list.presentation.model

import androidx.databinding.ViewDataBinding
import com.spitchenko.pokeapp.component.binderadapter.BindingClass
import kotlinx.android.parcel.Parcelize

private const val DEFAULT_ID = 3490343L

@Parcelize
object ProgressUiModel : BindingClass {

    @Suppress("PLUGIN_WARNING")
    override val itemId: Long = DEFAULT_ID

    override fun bind(viewDataBinding: ViewDataBinding) = Unit
}