package com.spitchenko.pokeapp.component.databinding.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingDiffUtilCallback

@BindingAdapter("onScrolledToFooter")
fun RecyclerView.setOnScrolledToFooter(action: () -> Unit) {
    addOnScrollListener(
        object : RecyclerView.OnScrollListener() {

            private val linearLayoutManager = layoutManager as LinearLayoutManager

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                with(linearLayoutManager) {
                    val visibleItemCount = childCount
                    val firstVisibleItemPosition = findFirstVisibleItemPosition()
                    val totalItemCount = itemCount

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                        action()
                    }
                }
            }
        })
}

@BindingAdapter(value = ["bindingList", "detectMoves"], requireAll = false)
fun RecyclerView.setBindingList(bindingList: List<BindingClass>?, detectMoves: Boolean?) {
    val bindingAdapter = adapter as? BinderAdapter ?: return

    val newOrEmptyList = bindingList.orEmpty()

    val bindingsDiffResult = DiffUtil.calculateDiff(
        BindingDiffUtilCallback(bindingAdapter.itemList, newOrEmptyList),
        detectMoves ?: false
    )

    bindingAdapter.setItems(bindingsDiffResult, newOrEmptyList)
}