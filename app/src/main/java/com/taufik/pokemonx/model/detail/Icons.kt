package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class Icons(
    @SerializedName("front_default")
    val frontDefault: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/1.png
    @SerializedName("front_female")
    val frontFemale: Any = Any() // null
)