package com.spitchenko.pokeapp.feature.list.presentation.model

import com.spitchenko.pokeapp.R
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.BindingClass
import com.spitchenko.pokeapp.feature.list.presentation.binderadapter.ViewType

private const val DEFAULT_ID = 3490343L

object ProgressUiModel: BindingClass {
    override val itemId: Long = DEFAULT_ID

    override val viewType: ViewType = ViewType(R.layout.item_progress)
}