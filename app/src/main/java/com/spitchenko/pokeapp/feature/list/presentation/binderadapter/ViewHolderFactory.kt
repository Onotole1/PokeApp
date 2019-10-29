package com.spitchenko.pokeapp.feature.list.presentation.binderadapter

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding


interface ViewHolderFactory {

    fun create(
        parent: ViewGroup,
        adapter: BinderAdapter
    ): BindingViewHolder<ViewBinding>
}