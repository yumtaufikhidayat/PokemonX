package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class Habitat(
    @SerializedName("name")
    val name: String = "", // mountain
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/pokemon-habitat/4/
)