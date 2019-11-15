package com.spitchenko.pokeapp.component.binderadapter

import androidx.databinding.ViewDataBinding

interface BindingClass {

	val itemId: Long

	fun areContentsTheSame(other: BindingClass): Boolean

	fun areItemsTheSame(other: BindingClass): Boolean = other.itemId == itemId

    fun bind(viewDataBinding: ViewDataBinding, position: Int)
}