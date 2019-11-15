package com.spitchenko.pokeapp.feature.list.presentation.binderadapter

import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

class BinderAdapter(
    private val factory: ArrayMap<KClass<out BindingClass>, ViewHolderFactory>
) : RecyclerView.Adapter<BindingViewHolder<ViewDataBinding>>() {

    var itemList: List<BindingClass> = emptyList()
        private set

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        val bindingItem = itemList.getOrNull(position) ?: return super.getItemViewType(position)

        return factory.indexOfKey(bindingItem::class)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<ViewDataBinding> =
        factory.valueAt(viewType).create(parent)

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

fun <T : BindingClass> binderAdapterOf(vararg factories: Pair<KClass<out T>, ViewHolderFactory>): BinderAdapter =
    BinderAdapter(arrayMapOf(*factories))