package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("slot")
    val slot: Int = 0, // 1
    @SerializedName("type")
    val type: TypeX = TypeX()
)