package com.spitchenko.pokeapp.feature.list.presentation.binderadapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class BinderAdapter(
    vararg factories: Pair<LayoutId, ViewHolderFactory>
) : RecyclerView.Adapter<BindingViewHolder<ViewDataBinding>>() {

    private val factory = mapOf(*factories)

    var itemList: List<BindingClass> = emptyList()
        private set

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int =
        itemList.getOrNull(position)?.layoutId ?: super.getItemViewType(position)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<ViewDataBinding> {
        val layoutId = LayoutId(viewType)

        return factory[layoutId]?.create(parent, layoutId, this) ?: BindingViewHolder(
            parent,
            layoutId
        )
    }

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