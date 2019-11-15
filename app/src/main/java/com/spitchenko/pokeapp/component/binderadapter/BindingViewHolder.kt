package com.spitchenko.pokeapp.component.binderadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder<out T : ViewDataBinding>(
    parent: ViewGroup,
    layout: LayoutId,
    val binding: T = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        layout.layoutRes,
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root)