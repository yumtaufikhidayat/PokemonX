package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("language")
    val language: LanguageX = LanguageX(),
    @SerializedName("name")
    val name: String = "" // リザードン
)