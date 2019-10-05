package com.spitchenko.pokeapp.feature.list.domain.usecase

import com.spitchenko.pokeapp.feature.list.domain.model.Pokemon

interface PokemonsRepository {

    suspend fun getPokemons(pageSize: Int, offset: Int): List<Pokemon>

    suspend fun refreshAndGetFirstPage(pageSize: Int): List<Pokemon>
}