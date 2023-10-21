package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class Variety(
    @SerializedName("is_default")
    val isDefault: Boolean = false, // true
    @SerializedName("pokemon")
    val pokemon: Pokemon = Pokemon()
)