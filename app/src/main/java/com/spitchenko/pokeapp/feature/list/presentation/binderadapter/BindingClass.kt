package com.spitchenko.pokeapp.feature.list.presentation.binderadapter

interface BindingClass: ViewTypeOwner {

    val itemId: Long
        get() = hashCode().toLong()

    fun areContentsTheSame(other: BindingClass): Boolean = equals(other)

    fun areItemsTheSame(other: BindingClass): Boolean = other.itemId == itemId
}