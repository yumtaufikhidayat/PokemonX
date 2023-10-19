package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int = 0, // 45
    @SerializedName("effort")
    val effort: Int = 0, // 0
    @SerializedName("stat")
    val stat: StatX = StatX()
)