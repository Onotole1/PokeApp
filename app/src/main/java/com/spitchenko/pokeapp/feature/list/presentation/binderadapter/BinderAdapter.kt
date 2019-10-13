package com.spitchenko.pokeapp.feature.list.presentation.binderadapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class BinderAdapter(
    private val viewHolderFabric: Map<Int, (parent: ViewGroup, adapter: BinderAdapter) -> BindingViewHolder<ViewDataBinding>> = emptyMap()
) : RecyclerView.Adapter<BindingViewHolder<ViewDataBinding>>() {

	var itemList: List<BindingClass> = emptyList()
		private set

	override fun getItemCount(): Int = itemList.size

	override fun getItemViewType(position: Int): Int = itemList.getOrNull(position)?.layoutId ?: super.getItemViewType(position)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<ViewDataBinding> =
		viewHolderFabric[viewType]?.invoke(parent, this) ?: BindingViewHolder(parent, viewType)

	fun setItems(diffResult: DiffUtil.DiffResult, items: List<BindingClass>) {
		itemList = items
		diffResult.dispatchUpdatesTo(this)
	}

    override fun onBindViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int) {
		val model = itemList.getOrNull(position) ?: return
        model.bind(holder.binding, position)
		holder.binding.executePendingBindings()
	}
}