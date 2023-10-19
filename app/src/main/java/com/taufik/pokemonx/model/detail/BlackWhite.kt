package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class BlackWhite(
    @SerializedName("animated")
    val animated: Animated = Animated(),
    @SerializedName("back_default")
    val backDefault: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/1.png
    @SerializedName("back_female")
    val backFemale: Any = Any(), // null
    @SerializedName("back_shiny")
    val backShiny: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/shiny/1.png
    @SerializedName("back_shiny_female")
    val backShinyFemale: Any = Any(), // null
    @SerializedName("front_default")
    val frontDefault: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/1.png
    @SerializedName("front_female")
    val frontFemale: Any = Any(), // null
    @SerializedName("front_shiny")
    val frontShiny: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/shiny/1.png
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any = Any() // null
)