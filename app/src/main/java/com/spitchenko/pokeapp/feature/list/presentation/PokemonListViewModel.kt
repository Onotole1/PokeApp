package com.spitchenko.pokeapp.feature.list.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.spitchenko.pokeapp.component.binderadapter.BindingClass
import com.spitchenko.pokeapp.component.extensions.*
import com.spitchenko.pokeapp.component.lifecycle.MutableSingleLiveEvent
import com.spitchenko.pokeapp.component.lifecycle.SingleLiveEvent
import com.spitchenko.pokeapp.component.lifecycle.delegate
import com.spitchenko.pokeapp.component.messaging.Message
import com.spitchenko.pokeapp.component.paging.MutablePagingUiModel
import com.spitchenko.pokeapp.component.paging.PagingState
import com.spitchenko.pokeapp.component.paging.PagingUiModel
import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon
import com.spitchenko.pokeapp.feature.list.domain.usecase.GetPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.domain.usecase.RefreshPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.presentation.model.ErrorUiModel
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonUiModel
import com.spitchenko.pokeapp.feature.list.presentation.model.ProgressUiModel
import com.spitchenko.pokeapp.feature.list.presentation.model.toUiModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PokemonListViewModel(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val refreshPokemonsUseCase: RefreshPokemonsUseCase,
    override val coroutineContext: CoroutineContext,
    private val pageSize: Int,
    private val viewModelJob: Job,
    savedStateHandle: SavedStateHandle
) : ViewModel(), CoroutineScope {

    private val _messageEvent = MutableSingleLiveEvent<Message>()
    val messageEvent: SingleLiveEvent<Message>
        get() = _messageEvent
    private val _uiModel = MutablePagingUiModel<BindingClass>(savedStateHandle)
    val uiModel: PagingUiModel<BindingClass>
        get() = _uiModel

    private var pokemons: List<PokemonUiModel> = emptyList()

    private var loadPageJob: Job? = null

    private var savedState: State by savedStateHandle.delegate { State.EMPTY }

    private var currentState: PokemonListState = Empty()
        private set(value) {
            savedState = value.state

            field = value
        }

    fun showNextPage(): Unit = currentState.showNextPage()

    fun refresh(): Unit = currentState.refresh()

    fun retry(): Unit = currentState.retry()

    fun restoreState() {
        when (savedState) {
            State.EMPTY_PROGRESS -> {
                currentState = EmptyProgress()
                currentState.refresh()
            }

            State.REFRESH -> {
                currentState = Refresh()
                currentState.refresh()
            }

            State.PAGE_PROGRESS -> {
                currentState = PageProgress()
                startLoading()
            }

            State.EMPTY -> {
                showNextPage()
            }

            State.DATA -> {
                currentState = Data()
            }

            State.PAGE_ERROR -> {
                currentState = PageError()
            }

            State.ALL_DATA -> {
                currentState = AllData()
            }

            State.EMPTY_DATA -> {
                currentState = EmptyData()
            }

            State.EMPTY_ERROR -> {
                currentState = EmptyError()
            }
        }
    }

    private interface PokemonListState : PagingState<PokemonUiModel> {
        val state: State
    }

    private inner class Empty : PokemonListState {

        override val state: State = State.EMPTY

        override fun showNextPage() {
            super.showNextPage()

            currentState = EmptyProgress()

            _uiModel.emptyProgressVisible.value = true

            startLoading()
        }
    }

    private inner class EmptyProgress : PokemonListState {

        override val state: State = State.EMPTY_PROGRESS

        override fun refresh() {
            super.refresh()

            startLoading()
        }

        override fun newData(data: List<PokemonUiModel>) {
            super.newData(data)

            if (data.isNotEmpty()) {
                currentState = if (data.size == pageSize) Data() else AllData()
                pokemons = data
                _uiModel.data.clearAndFill(data)

                with(_uiModel) {
                    dataVisible.value = true
                    emptyProgressVisible.value = false
                    refreshProgressVisible.value = false
                }
            } else {
                currentState = EmptyData()

                with(_uiModel) {
                    pokemons = emptyList()
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

    private inner class EmptyError : PokemonListState {

        override val state: State = State.EMPTY_ERROR

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

    private inner class EmptyData : PokemonListState {

        override val state: State = State.EMPTY_DATA

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

    private inner class Data : PokemonListState {

        override val state: State = State.DATA

        override fun refresh() {
            super.refresh()

            currentState = Refresh()

            _uiModel.refreshProgressVisible.value = true

            refreshLoading()
        }

        override fun showNextPage() {
            super.showNextPage()

            currentState = PageProgress()

            _uiModel.data.add(ProgressUiModel)

            startLoading()
        }
    }

    private inner class PageError : PokemonListState {

        override val state: State = State.PAGE_ERROR

        override fun refresh() {
            super.refresh()

            currentState = Refresh()

            with(_uiModel) {
                data.removeLast()
                refreshProgressVisible.value = true
            }

            refreshLoading()
        }

        override fun retry() {
            super.retry()

            currentState = PageProgress()

            with(_uiModel) {
                data.transaction {
                    set(lastIndex, ProgressUiModel)
                }
            }

            startLoading()
        }
    }

    private inner class Refresh : PokemonListState {

        override val state: State = State.REFRESH

        override fun refresh() {
            super.refresh()

            startLoading()
        }

        override fun newData(data: List<PokemonUiModel>) {
            super.newData(data)

            pokemons = data
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

            _uiModel.refreshProgressVisible.value = false

            _messageEvent.sendEvent(message)
        }
    }

    private inner class PageProgress : PokemonListState {

        override val state: State = State.PAGE_PROGRESS

        override fun newData(data: List<PokemonUiModel>) {
            super.newData(data)

            pokemons = pokemons + data
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
                data.transaction {
                    set(lastIndex, ErrorUiModel(message))
                }
            }
        }
    }

    private inner class AllData : PokemonListState {

        override val state: State = State.ALL_DATA

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
                val listSize = uiModel.data.size()

                val nextPage = getPokemonsUseCase(pageSize, listSize).map(Pokemon::toUiModel)

                currentState.newData(nextPage)
            } catch (exception: CancellationException) {
                throw exception
            } catch (throwable: Throwable) {
                fail(throwable)
            }
        }
    }

    private fun refreshLoading() {
        viewModelJob.cancelChildren()
        launch {
            try {
                val newPage = refreshPokemonsUseCase(pageSize).map(Pokemon::toUiModel)

                _uiModel.data.clear()
                currentState.newData(newPage)
            } catch (cancellationException: CancellationException) {
                throw cancellationException
            } catch (throwable: Throwable) {
                fail(throwable)
            }
        }
    }

    private fun fail(throwable: Throwable) {
        throwable.toUserFriendlyError()
            .also(currentState::fail)
    }

    override fun onCleared() {
        viewModelJob.cancel()
    }

    private enum class State {
        EMPTY,
        EMPTY_PROGRESS,
        EMPTY_ERROR,
        EMPTY_DATA,
        DATA,
        PAGE_ERROR,
        REFRESH,
        PAGE_PROGRESS,
        ALL_DATA
    }
}