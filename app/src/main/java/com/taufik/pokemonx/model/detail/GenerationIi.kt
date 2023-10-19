package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class GenerationIi(
    @SerializedName("crystal")
    val crystal: Crystal = Crystal(),
    @SerializedName("gold")
    val gold: Gold = Gold(),
    @SerializedName("silver")
    val silver: Silver = Silver()
)