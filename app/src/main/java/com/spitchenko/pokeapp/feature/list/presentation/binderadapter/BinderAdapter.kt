package com.spitchenko.pokeapp.feature.list.presentation.binderadapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

open class BinderAdapter<DB : ViewDataBinding>: RecyclerView.Adapter<BindingViewHolder<DB>>() {

	var itemList: List<BindingClass> = emptyList()
		private set

	override fun getItemCount(): Int = itemList.size

	override fun getItemViewType(position: Int): Int = itemList.getOrNull(position)?.layoutId ?: super.getItemViewType(position)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<DB> =
		BindingViewHolder(parent, viewType)

	fun setItems(diffResult: DiffUtil.DiffResult, items: List<BindingClass>) {
		itemList = items
		diffResult.dispatchUpdatesTo(this)
	}

	override fun onBindViewHolder(holder: BindingViewHolder<DB>, position: Int) {
		val model = itemList.getOrNull(position) ?: return
		model.bind(holder.binding)
		holder.binding.executePendingBindings()
	}
}