package com.taufik.pokemonx.model.species


import com.google.gson.annotations.SerializedName

data class PalParkEncounter(
    @SerializedName("area")
    val area: Area = Area(),
    @SerializedName("base_score")
    val baseScore: Int = 0, // 90
    @SerializedName("rate")
    val rate: Int = 0 // 3
)