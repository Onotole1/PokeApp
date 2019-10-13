package com.spitchenko.pokeapp.feature.list.data.network

import com.spitchenko.pokeapp.feature.list.data.network.model.PokemonDetailsNetworkDto
import com.spitchenko.pokeapp.feature.list.domain.model.*
import javax.inject.Inject

interface PokemonDetailsNetworkConverter {

    fun convert(response: PokemonDetailsNetworkDto): PokemonDetails
}

private const val ATTACK_STAT = "attack"
private const val DEFENSE_STAT = "defense"
private const val HEALTH_STAT = "hp"

class PokemonDetailsNetworkConverterImpl @Inject constructor(
    private val typeNetworkConverter: PokemonTypeNetworkConverter,
    private val pictureConverter: PokemonPictureConverter
) : PokemonDetailsNetworkConverter {

    override fun convert(response: PokemonDetailsNetworkDto): PokemonDetails {
        val attack = Attack(
            getStat(ATTACK_STAT, response.stats)
        )

        val defense = Defense(
            getStat(DEFENSE_STAT, response.stats)
        )

        val health = Health(
            getStat(HEALTH_STAT, response.stats)
        )

        val type = typeNetworkConverter.convert(response.types)

        val picture = pictureConverter.convert(response.sprites)

        return PokemonDetails(
            name = response.name,
            height = Centimeters(response.height),
            weight = Kilograms(response.weight),
            attack = attack,
            defense = defense,
            health = health,
            type = type,
            image = picture
        )
    }

    private fun getStat(name: String, stats: List<PokemonDetailsNetworkDto.Stat>): Int =
        stats.first {
            it.stat.name == name
        }.baseStat
}