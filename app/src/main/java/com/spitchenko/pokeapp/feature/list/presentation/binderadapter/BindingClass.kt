package com.spitchenko.pokeapp.feature.list.presentation.binderadapter

import androidx.databinding.ViewDataBinding

interface BindingClass {

	val layoutId: Int

	val itemId: Long

	fun areContentsTheSame(other: BindingClass): Boolean

	fun areItemsTheSame(other: BindingClass): Boolean = other.itemId == itemId

    fun bind(viewDataBinding: ViewDataBinding, position: Int)
}