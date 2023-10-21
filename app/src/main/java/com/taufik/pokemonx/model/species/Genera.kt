package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class Genera(
    @SerializedName("genus")
    val genus: String = "", // かえんポケモン
    @SerializedName("language")
    val language: LanguageX = LanguageX()
)