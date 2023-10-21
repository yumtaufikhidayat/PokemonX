package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class Shape(
    @SerializedName("name")
    val name: String = "", // upright
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/pokemon-shape/6/
)