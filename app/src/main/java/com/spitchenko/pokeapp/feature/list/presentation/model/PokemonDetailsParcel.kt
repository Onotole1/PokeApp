package com.spitchenko.pokeapp.feature.list.presentation.model

import android.os.Parcel
import android.os.Parcelable
import com.spitchenko.pokeapp.feature.list.domain.model.*

data class PokemonDetailsParcel(val details: Pokemon) : Parcelable {
    constructor(parcel: Parcel) : this(
        Pokemon(
            requireNotNull(parcel.readString()),
            Centimeters(parcel.readInt()),
            Kilograms(parcel.readInt()),
            Attack(parcel.readInt()),
            Defense(parcel.readInt()),
            Health(parcel.readInt()),
            Type(requireNotNull(parcel.readString())),
            parcel.readString()?.let {
                PokemonPicture(it)
            }
        )
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(details.name)
        parcel.writeInt(details.height.value)
        parcel.writeInt(details.weight.value)
        parcel.writeInt(details.attack.value)
        parcel.writeInt(details.defense.value)
        parcel.writeInt(details.health.value)
        parcel.writeString(details.type.value)
        parcel.writeString(details.image?.url)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<PokemonDetailsParcel> {
        override fun createFromParcel(parcel: Parcel): PokemonDetailsParcel =
            PokemonDetailsParcel(parcel)

        override fun newArray(size: Int): Array<PokemonDetailsParcel?> = arrayOfNulls(size)
    }
}

fun Pokemon.toParcel() = PokemonDetailsParcel(this)