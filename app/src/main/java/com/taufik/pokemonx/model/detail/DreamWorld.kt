package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class DreamWorld(
    @SerializedName("front_default")
    val frontDefault: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg
    @SerializedName("front_female")
    val frontFemale: Any = Any() // null
)