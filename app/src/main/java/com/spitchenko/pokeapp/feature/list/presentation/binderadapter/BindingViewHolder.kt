package com.spitchenko.pokeapp.feature.list.presentation.binderadapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BindingViewHolder<out T : ViewBinding>(
    val binding: T
) : RecyclerView.ViewHolder(binding.root)