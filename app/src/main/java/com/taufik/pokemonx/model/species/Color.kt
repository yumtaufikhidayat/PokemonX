package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class Color(
    @SerializedName("name")
    val name: String = "", // red
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/pokemon-color/8/
)