package com.spitchenko.pokeapp.feature.list.presentation.model

import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.component.messaging.Message
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewType
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewTypeOwner

private const val DEFAULT_ID = 9054434L

data class ErrorUiModel(
    val message: Message
): BindingClass, ViewTypeOwner by ErrorUiModel {

    override val itemId: Long = DEFAULT_ID

    companion object: ViewTypeOwner {
        override val viewType: ViewType = ViewType(R.layout.item_error)
    }
}