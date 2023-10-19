package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("back_default")
    val backDefault: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png
    @SerializedName("back_female")
    val backFemale: Any = Any(), // null
    @SerializedName("back_shiny")
    val backShiny: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/1.png
    @SerializedName("back_shiny_female")
    val backShinyFemale: Any = Any(), // null
    @SerializedName("front_default")
    val frontDefault: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png
    @SerializedName("front_female")
    val frontFemale: Any = Any(), // null
    @SerializedName("front_shiny")
    val frontShiny: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any = Any(), // null
    @SerializedName("other")
    val other: Other = Other(),
    @SerializedName("versions")
    val versions: Versions = Versions()
)