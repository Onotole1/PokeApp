package com.spitchenko.pokeapp.component.binderadapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding


interface ViewHolderFactory {

    fun create(parent: ViewGroup): BindingViewHolder<ViewDataBinding>
}