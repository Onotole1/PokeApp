package com.spitchenko.pokeapp.component.binderadapter

import android.os.Parcelable
import androidx.databinding.ViewDataBinding

interface BindingClass: Parcelable {

	val itemId: Long

	fun areContentsTheSame(other: BindingClass): Boolean = other == this

	fun areItemsTheSame(other: BindingClass): Boolean = other.itemId == itemId

    fun bind(viewDataBinding: ViewDataBinding)
}