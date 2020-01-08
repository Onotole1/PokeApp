package com.spitchenko.pokeapp.feature.details.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonDetailsUiModel(
    val name: String,
    val imageUrl: String?,
    val details: List<PokemonDetailUiModel>
): Parcelable