package com.spitchenko.pokeapp.feature.list.data.network.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailsNetworkDto(
    val id: Long,
    val height: Int,
    val name: String,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) {

    data class Sprites(
        @SerializedName("back_default")
        val backDefault: String?,
        @SerializedName("back_female")
        val backFemale: String?,
        @SerializedName("back_shiny")
        val backShiny: String?,
        @SerializedName("back_shiny_female")
        val backShinyFemale: String?,
        @SerializedName("front_default")
        val frontDefault: String?,
        @SerializedName("front_female")
        val frontFemale: String?,
        @SerializedName("front_shiny")
        val frontShiny: String?,
        @SerializedName("front_shiny_female")
        val frontShinyFemale: String?
    )

    data class Stat(
        @SerializedName("base_stat")
        val baseStat: Int,
        val effort: Int,
        val stat: Stat
    ) {
        data class Stat(
            val name: String,
            val url: String
        )
    }

    data class Type(
        val slot: Int,
        val type: Type
    ) {
        data class Type(
            val name: String,
            val url: String
        )
    }
}