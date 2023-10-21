package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class LanguageX(
    @SerializedName("name")
    val name: String = "", // ja-Hrkt
    @SerializedName("url")
    val url: String = "" // https://pokeapi.co/api/v2/language/1/
)