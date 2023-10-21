package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name")
    val name: String = "", // charizard
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/pokemon/6/
)