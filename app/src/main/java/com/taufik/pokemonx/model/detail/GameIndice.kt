package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class GameIndice(
    @SerializedName("game_index")
    val gameIndex: Int = 0, // 153
    @SerializedName("version")
    val version: Version = Version()
)