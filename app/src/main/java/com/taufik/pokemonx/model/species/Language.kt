package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class Language(
    @SerializedName("name")
    val name: String = "", // en
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/language/9/
)