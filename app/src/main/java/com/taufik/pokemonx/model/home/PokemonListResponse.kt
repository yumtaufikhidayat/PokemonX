package com.taufik.pokemonx.model.home


import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count")
    val count: Int = 0, // 1292
    @SerializedName("next")
    val next: String = "", // https://pokeapi.co/api/v2/pokemon/?offset=20&limit=20
    @SerializedName("previous")
    val previous: Any = Any(), // null
    @SerializedName("results")
    val results: List<PokemonListResult> = listOf()
)