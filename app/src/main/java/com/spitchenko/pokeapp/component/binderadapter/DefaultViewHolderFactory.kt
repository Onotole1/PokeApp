package com.spitchenko.pokeapp.component.binderadapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

class DefaultViewHolderFactory(private val layoutId: LayoutId) : ViewHolderFactory {

    override fun create(parent: ViewGroup): BindingViewHolder<ViewDataBinding> =
        BindingViewHolder(parent, layoutId)
}