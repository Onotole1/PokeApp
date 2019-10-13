package com.spitchenko.pokeapp.feature.list.data.network.api

import com.spitchenko.pokeapp.feature.list.data.network.model.PokemonDetailsNetworkDto
import com.spitchenko.pokeapp.feature.list.data.network.model.PokemonsListNetworkDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonDetailsApi {

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name") name: String
    ): PokemonDetailsNetworkDto
}