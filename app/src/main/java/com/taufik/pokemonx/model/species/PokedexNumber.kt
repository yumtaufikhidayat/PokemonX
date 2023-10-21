package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class PokedexNumber(
    @SerializedName("entry_number")
    val entryNumber: Int = 0, // 6
    @SerializedName("pokedex")
    val pokedex: Pokedex = Pokedex()
)