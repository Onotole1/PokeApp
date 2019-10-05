package com.spitchenko.pokeapp.component.paging

interface ListViewModel<T> {

    val uiModel: PagingUiModel<T>
    fun showNextPage()
    fun refresh()
}