package com.spitchenko.pokeapp.feature.list.presentation.binderadapter

import androidx.recyclerview.widget.DiffUtil
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass

open class BindingDiffUtilCallback(
    private val oldItems: List<BindingClass>,
    private val newItems: List<BindingClass>
) : DiffUtil.Callback() {

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return oldItems[oldItemPosition].areItemsTheSame(newItems[newItemPosition])
	}

	override fun getOldListSize(): Int = oldItems.size

	override fun getNewListSize(): Int = newItems.size

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return oldItems[oldItemPosition].areContentsTheSame(newItems[newItemPosition])
	}
}