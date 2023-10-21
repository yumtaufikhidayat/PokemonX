package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class EvolvesFromSpecies(
    @SerializedName("name")
    val name: String = "", // charmeleon
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/pokemon-species/5/
)