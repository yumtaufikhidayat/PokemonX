package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class Area(
    @SerializedName("name")
    val name: String = "", // field
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/pal-park-area/2/
)