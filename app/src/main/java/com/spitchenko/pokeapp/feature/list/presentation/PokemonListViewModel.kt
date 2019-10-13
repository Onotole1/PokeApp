package com.spitchenko.pokeapp.feature.list.presentation

import androidx.lifecycle.ViewModel
import com.spitchenko.pokeapp.component.extensions.*
import com.spitchenko.pokeapp.component.lifecycle.MutableSingleLiveEvent
import com.spitchenko.pokeapp.component.lifecycle.SingleLiveEvent
import com.spitchenko.pokeapp.component.messaging.Message
import com.spitchenko.pokeapp.component.paging.MutablePagingUiModel
import com.spitchenko.pokeapp.component.paging.PagingState
import com.spitchenko.pokeapp.component.paging.PagingUiModel
import com.spitchenko.pokeapp.feature.list.domain.usecase.GetPokemonDetailsUseCase
import com.spitchenko.pokeapp.feature.list.domain.usecase.GetPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.domain.usecase.RefreshPokemonsUseCase
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.presentation.model.ErrorUiModel
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonState
import com.spitchenko.pokeapp.feature.list.presentation.model.PokemonUiModel
import com.spitchenko.pokeapp.feature.list.presentation.model.ProgressUiModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PokemonListViewModel(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val refreshPokemonsUseCase: RefreshPokemonsUseCase,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
    override val coroutineContext: CoroutineContext,
    private val pageSize: Int,
    private val viewModelJob: Job
) : ViewModel(), CoroutineScope {

    private val _messageEvent = MutableSingleLiveEvent<Message>()
    val messageEvent: SingleLiveEvent<Message>
        get() = _messageEvent
    private val _uiModel = MutablePagingUiModel<BindingClass>()
    val uiModel: PagingUiModel<BindingClass>
        get() = _uiModel

    private var pokemons: List<PokemonUiModel> = emptyList()

    private val progressUiModel = ProgressUiModel()

    private var loadPageJob: Job? = null

    private var currentState: PagingState<PokemonUiModel> = Empty()

    fun showNextPage(): Unit = currentState.showNextPage()

    fun refresh(): Unit = currentState.refresh()

    fun retry(): Unit = currentState.retry()

    fun retryItem(index: Int, item: PokemonUiModel) {
        launch {
            try {
                _uiModel.data.set(
                    index,
                    PokemonUiModel(PokemonState.Progress(item.pokemonState.name))
                )

                val pokemonDetails = getPokemonDetailsUseCase(item.pokemonState.name)

                _uiModel.data.set(
                    index, PokemonUiModel(
                        PokemonState.Data(pokemonDetails)
                    )
                )
            } catch (cancellationException: CancellationException) {
                throw cancellationException
            } catch (throwable: Throwable) {
                failItem(index, item, throwable)
            }
        }
    }

    private inner class Empty : PagingState<PokemonUiModel> {

        override fun showNextPage() {
            super.showNextPage()

            currentState = EmptyProgress()

            _uiModel.emptyProgressVisible.value = true

            startLoading()
        }
    }

    private inner class EmptyProgress : PagingState<PokemonUiModel> {

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

    private inner class EmptyError : PagingState<PokemonUiModel> {

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

    private inner class EmptyData : PagingState<PokemonUiModel> {

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

    private inner class Data : PagingState<PokemonUiModel> {

        override fun refresh() {
            super.refresh()

            currentState = Refresh()

            _uiModel.refreshProgressVisible.value = true

            refreshLoading()
        }

        override fun showNextPage() {
            super.showNextPage()

            currentState = PageProgress()

            _uiModel.data.add(progressUiModel)

            startLoading()
        }
    }

    private inner class PageError : PagingState<PokemonUiModel> {

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
                    set(lastIndex, progressUiModel)
                }
            }

            startLoading()
        }
    }

    private inner class Refresh : PagingState<PokemonUiModel> {

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

            with(_uiModel) {
                refreshProgressVisible.value = false
            }

            _messageEvent.sendEvent(message)
        }
    }

    private inner class PageProgress : PagingState<PokemonUiModel> {

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

    private inner class AllData : PagingState<PokemonUiModel> {

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

                val nextPage = getPokemonsUseCase(pageSize, listSize).map {
                    PokemonUiModel(PokemonState.Progress(it.name))
                }

                nextPage.forEachIndexed { index, pokemonUiModel ->
                    loadDetails(index + pokemons.size, pokemonUiModel)
                }

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
                val newPage = refreshPokemonsUseCase(pageSize).map {
                    PokemonUiModel(PokemonState.Progress(it.name))
                }

                newPage.forEachIndexed(::loadDetails)

                _uiModel.data.clear()
                currentState.newData(newPage)
            } catch (cancellationException: CancellationException) {
                throw cancellationException
            } catch (throwable: Throwable) {
                fail(throwable)
            }
        }
    }

    private fun loadDetails(index: Int, item: PokemonUiModel) {
        launch {
            try {
                val pokemonDetails = getPokemonDetailsUseCase(item.pokemonState.name)

                _uiModel.data.set(
                    index, PokemonUiModel(
                        PokemonState.Data(pokemonDetails)
                    )
                )
            } catch (cancellationException: CancellationException) {
                throw cancellationException
            } catch (throwable: Throwable) {
                failItem(index, item, throwable)
            }
        }
    }

    private fun failItem(index: Int, item: PokemonUiModel, throwable: Throwable) {
        _uiModel.data.set(
            index,
            PokemonUiModel(
                PokemonState.Error(
                    item.pokemonState.name,
                    throwable.toUserFriendlyError()
                )
            )
        )
    }

    private fun fail(throwable: Throwable) {
        throwable.toUserFriendlyError()
            .also(currentState::fail)
    }

    override fun onCleared() {
        viewModelJob.cancel()
    }
}