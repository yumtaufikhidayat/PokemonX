package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class GenerationViii(
    @SerializedName("icons")
    val icons: IconsX = IconsX()
)