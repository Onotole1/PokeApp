package com.spitchenko.pokeapp.feature.list.presentation.binderadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder<out T : ViewDataBinding>(
    parent: ViewGroup,
    @LayoutRes layout: Int,
    val binding: T = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        layout,
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root)