package com.spitchenko.pokeapp.feature.list.data.network.api

import com.spitchenko.pokeapp.feature.list.data.network.model.PokemonsListNetworkDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonListApi {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonsListNetworkDto
}