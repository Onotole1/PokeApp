package com.spitchenko.pokeapp.feature.list.presentation.binderadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BinderAdapter(
    private val factory: Map<ViewType, ViewHolderFactory>,
    private val renders: Map<ViewType, ViewRenderer> = emptyMap()
) : RecyclerView.Adapter<BindingViewHolder<ViewBinding>>() {

    var itemList: List<BindingClass> = emptyList()
        private set

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int =
        itemList.getOrNull(position)?.viewType?.type ?: super.getItemViewType(position)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<ViewBinding> =
        factory.getValue(ViewType(viewType)).create(parent, this)

    fun setItems(diffResult: DiffUtil.DiffResult, items: List<BindingClass>) {
        itemList = items
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ViewBinding>, position: Int) {
        val model = itemList.getOrNull(position) ?: return
        val viewType = ViewType(holder.itemViewType)
        renders[viewType]?.render(model, holder, position)
    }
}