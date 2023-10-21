package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class EvolutionChain(
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/evolution-chain/2/
)