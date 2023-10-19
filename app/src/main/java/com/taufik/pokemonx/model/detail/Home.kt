package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class Home(
    @SerializedName("front_default")
    val frontDefault: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/1.png
    @SerializedName("front_female")
    val frontFemale: Any = Any(), // null
    @SerializedName("front_shiny")
    val frontShiny: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/1.png
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any = Any() // null
)