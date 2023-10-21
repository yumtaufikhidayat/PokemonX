package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class EggGroup(
    @SerializedName("name")
    val name: String = "", // monster
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/egg-group/1/
)