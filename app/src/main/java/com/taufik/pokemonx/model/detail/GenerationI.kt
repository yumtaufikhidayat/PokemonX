package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue")
    val redBlue: RedBlue = RedBlue(),
    @SerializedName("yellow")
    val yellow: Yellow = Yellow()
)