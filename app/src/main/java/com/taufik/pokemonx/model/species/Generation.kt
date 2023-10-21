package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class Generation(
    @SerializedName("name")
    val name: String = "", // generation-i
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/generation/1/
)