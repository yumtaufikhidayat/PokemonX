package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class Version(
    @SerializedName("name")
    val name: String = "", // red
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/version/1/
)