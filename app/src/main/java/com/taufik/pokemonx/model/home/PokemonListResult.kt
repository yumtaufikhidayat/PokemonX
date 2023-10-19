package com.taufik.pokemonx.model.home


import com.google.gson.annotations.SerializedName

data class PokemonListResult(
    @SerializedName("name")
    val name: String = "", // bulbasaur
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/pokemon/1/
)