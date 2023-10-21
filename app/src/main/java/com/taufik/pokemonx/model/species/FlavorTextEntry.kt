package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class FlavorTextEntry(
    @SerializedName("flavor_text")
    val flavorText: String = "", // Spits fire thatis hot enough tomelt boulders.Known to causeforest firesunintentionally.
    @SerializedName("language")
    val language: Language = Language(),
    @SerializedName("version")
    val version: Version = Version()
)