package com.spitchenko.pokeapp.feature.list.presentation

import androidx.viewbinding.ViewBinding
import com.spitchenko.pokeapp.component.extensions.setTextIfDifferent
import com.spitchenko.pokeapp.databinding.ItemErrorBinding
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingViewHolder
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewRenderer
import com.spitchenko.pokeapp.feature.list.presentation.model.ErrorUiModel

object ErrorViewRenderer : ViewRenderer {

    override fun render(
        item: BindingClass,
        viewHolder: BindingViewHolder<ViewBinding>,
        position: Int
    ) {
        if (item !is ErrorUiModel || viewHolder.binding !is ItemErrorBinding) {
            return
        }

        viewHolder.binding.errorMessage.setTextIfDifferent(item.message)
    }
}