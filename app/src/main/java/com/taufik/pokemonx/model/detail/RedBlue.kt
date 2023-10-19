package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class RedBlue(
    @SerializedName("back_default")
    val backDefault: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/back/1.png
    @SerializedName("back_gray")
    val backGray: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/back/gray/1.png
    @SerializedName("back_transparent")
    val backTransparent: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/transparent/back/1.png
    @SerializedName("front_default")
    val frontDefault: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/1.png
    @SerializedName("front_gray")
    val frontGray: String = "", // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/gray/1.png
    @SerializedName("front_transparent")
    val frontTransparent: String = "" // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/transparent/1.png
)