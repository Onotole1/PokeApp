package com.spitchenko.pokeapp.component.paging

import com.spitchenko.pokeapp.component.extensions.*
import com.spitchenko.pokeapp.component.messaging.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModelDelegate<T>(
    override val coroutineContext: CoroutineContext,
    private val pageSize: Int,
    private val paginator: Paginator<T>
) : CoroutineScope, ListViewModel<T> {

    private val _uiModel = MutablePagingUiModel<T>()
    override val uiModel: PagingUiModel<T>
        get() = _uiModel

    private var loadPageJob: Job? = null

    private var currentState: State<T> = Empty()

    override fun showNextPage(): Unit = currentState.showNextPage()

    override fun refresh(): Unit = currentState.refresh()

    private inner class Empty : State<T> {

        override fun refresh() {
            super.refresh()

            currentState = EmptyProgress()

            _uiModel.emptyProgressVisible.value = true

            startLoading()
        }

        override fun showNextPage() {
          super.showNextPage()

            refresh()
        }
    }

    private inner class EmptyProgress : State<T> {

        override fun refresh() {
            super.refresh()

            startLoading()
        }

        override fun newData(data: List<T>) {
            super.newData(data)

            if (data.isNotEmpty()) {
                currentState = if (data.size == pageSize) Data() else AllData()
                _uiModel.data.clearAndFill(data)

                with(_uiModel) {
                    dataVisible.value = true
                    emptyProgressVisible.value = false
                    refreshProgressVisible.value = false
                }
            } else {
                currentState = EmptyData()

                with(_uiModel) {
                    this.data.clear()
                    emptyProgressVisible.value = false
                    refreshProgressVisible.value = false
                    emptyVisible.value = true
                }
            }
        }

        override fun fail(message: Message) {
            super.fail(message)

            currentState = EmptyError()

            with(_uiModel) {
                emptyProgressVisible.value = false
                refreshProgressVisible.value = false
                emptyError.value = message
                emptyErrorVisible.value = true
            }
        }
    }

    private inner class EmptyError : State<T> {

        override fun refresh() {
            super.refresh()

            currentState = EmptyProgress()

            with(_uiModel) {
                emptyErrorVisible.value = false
                emptyProgressVisible.value = true
            }

            refreshLoading()
        }
    }

    private inner class EmptyData : State<T> {

        override fun refresh() {
            super.refresh()

            currentState = EmptyProgress()

            with(_uiModel) {
                emptyVisible.value = false
                emptyProgressVisible.value = true
            }

            startLoading()
        }
    }

    private inner class Data : State<T> {

        override fun refresh() {
            super.refresh()

            currentState = Refresh()

            _uiModel.refreshProgressVisible.value = true

            refreshLoading()
        }

        override fun showNextPage() {
            super.showNextPage()

            currentState = PageProgress()

            _uiModel.data.add(paginator.progressItem)

            startLoading()
        }
    }

    private inner class PageError : State<T> {

        override fun refresh() {
            super.refresh()

            currentState = Refresh()

            with(_uiModel) {
                pageErrorVisible.value = false
                refreshProgressVisible.value = true
            }

            refreshLoading()
        }

        override fun showNextPage() {
            super.showNextPage()

            currentState = PageProgress()

            with(_uiModel) {
                data.add(paginator.progressItem)
                pageErrorVisible.value = false
            }

            startLoading()
        }
    }

    private inner class Refresh : State<T> {

        override fun refresh() {
            super.refresh()

            startLoading()
        }

        override fun newData(data: List<T>) {
            super.newData(data)

            if (data.isNotEmpty()) {
                currentState = if (data.size == pageSize) Data() else AllData()

                with(_uiModel) {
                    this.data.value = data
                    refreshProgressVisible.value = false
                    dataVisible.value = true
                }
            } else {
                currentState = EmptyData()
                _uiModel.data.clear()

                with(_uiModel) {
                    this.data.clear()
                    dataVisible.value = false
                    refreshProgressVisible.value = false
                    emptyVisible.value = true
                }
            }
        }

        override fun fail(message: Message) {
            super.fail(message)

            currentState = Data()

            with(_uiModel) {
                refreshProgressVisible.value = false
                errorMessage.value = message
            }
        }
    }

    private inner class PageProgress : State<T> {

        override fun newData(data: List<T>) {
            super.newData(data)

            if (data.isNotEmpty()) {
                currentState = if (data.size == pageSize) Data() else AllData()

                with(_uiModel) {
                    this.data.transaction {
                        removeAt(lastIndex)
                        addAll(data)
                    }
                    dataVisible.value = true
                }
            } else {
                currentState = AllData()

                _uiModel.data.removeLast()
            }
        }

        override fun refresh() {
            super.refresh()

            currentState = Refresh()

            with(_uiModel) {
                data.removeLast()
                refreshProgressVisible.value = true
            }

            refreshLoading()
        }

        override fun fail(message: Message) {
            super.fail(message)

            currentState = PageError()

            with(_uiModel) {
                data.removeLast()
                errorMessage.value = message
                pageErrorVisible.value = true
            }
        }
    }

    private inner class AllData : State<T> {

        override fun refresh() {
            super.refresh()

            currentState = Refresh()

            _uiModel.refreshProgressVisible.value = true

            refreshLoading()
        }
    }

    private fun startLoading() {
        loadPageJob?.cancel()
        loadPageJob = launch {
            try {
                val listSize = uiModel.data.value?.size ?: 0

                val nextPage = paginator.getNextPage(listSize)

                currentState.newData(nextPage)
            } catch (throwable: Throwable) {
                fail(throwable)
            }
        }
    }

    private fun refreshLoading() {
        loadPageJob?.cancel()
        loadPageJob = launch {
            try {
                val newPage = paginator.resetPagingAndGetFirstPage()
                _uiModel.data.clear()
                currentState.newData(newPage)
            } catch (throwable: Throwable) {
                fail(throwable)
            }
        }
    }

    private fun fail(throwable: Throwable) {
        throwable.toUserFriendlyError()
            .also(currentState::fail)
    }
}
