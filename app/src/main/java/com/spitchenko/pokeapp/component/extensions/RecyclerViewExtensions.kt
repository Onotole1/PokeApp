package com.spitchenko.pokeapp.component.extensions

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BinderAdapter
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingDiffUtilCallback

inline fun RecyclerView.setOnScrolledToFooter(crossinline action: () -> Unit) {
    addOnScrollListener(
        object : RecyclerView.OnScrollListener() {

            private val linearLayoutManager = layoutManager as LinearLayoutManager

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                with(linearLayoutManager) {
                    val visibleItemCount = childCount
                    val firstVisibleItemPosition = findFirstVisibleItemPosition()
                    val totalItemCount = itemCount

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                        post {
                            action()
                        }
                    }
                }
            }
        })
}

fun RecyclerView.setBindingList(
    bindingList: List<BindingClass>?,
    detectMoves: Boolean = false
) {
    val bindingAdapter = adapter as? BinderAdapter ?: return

    val newOrEmptyList = bindingList.orEmpty()

    val bindingsDiffResult = DiffUtil.calculateDiff(
        BindingDiffUtilCallback(bindingAdapter.itemList, newOrEmptyList),
        detectMoves
    )

    bindingAdapter.setItems(bindingsDiffResult, newOrEmptyList)
}