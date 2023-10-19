package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class Ability(
    @SerializedName("ability")
    val ability: AbilityX = AbilityX(),
    @SerializedName("is_hidden")
    val isHidden: Boolean = false, // false
    @SerializedName("slot")
    val slot: Int = 0 // 1
)