package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class Pokedex(
    @SerializedName("name")
    val name: String = "", // national
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/pokedex/1/
)