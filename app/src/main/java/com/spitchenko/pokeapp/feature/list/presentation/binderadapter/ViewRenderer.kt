package com.spitchenko.pokeapp.feature.list.presentation.binderadapter

import androidx.viewbinding.ViewBinding

interface ViewRenderer {

    fun render(item: BindingClass, viewHolder: BindingViewHolder<ViewBinding>, position: Int)
}