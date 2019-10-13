package com.spitchenko.pokeapp.feature.list.presentation.binderadapter

import androidx.recyclerview.widget.DiffUtil

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

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return Change(
            oldItem,
            newItem
        )
    }

    data class Change(
        val oldItem: BindingClass,
        val newItem: BindingClass
    )
}