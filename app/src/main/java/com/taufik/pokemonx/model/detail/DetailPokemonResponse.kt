package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class DetailPokemonResponse(
    @SerializedName("abilities")
    val abilities: List<Ability> = listOf(),
    @SerializedName("base_experience")
    val baseExperience: Int = 0, // 64
    @SerializedName("forms")
    val forms: List<Form> = listOf(),
    @SerializedName("game_indices")
    val gameIndices: List<GameIndice> = listOf(),
    @SerializedName("height")
    val height: Int = 0, // 7
    @SerializedName("held_items")
    val heldItems: List<Any> = listOf(),
    @SerializedName("id")
    val id: Int = 0, // 1
    @SerializedName("is_default")
    val isDefault: Boolean = false, // true
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String = "", // https://pokeapi.co/api/v2/pokemon/1/encounters
    @SerializedName("moves")
    val moves: List<Move> = listOf(),
    @SerializedName("name")
    val name: String = "", // bulbasaur
    @SerializedName("order")
    val order: Int = 0, // 1
    @SerializedName("past_abilities")
    val pastAbilities: List<Any> = listOf(),
    @SerializedName("past_types")
    val pastTypes: List<Any> = listOf(),
    @SerializedName("species")
    val species: Species = Species(),
    @SerializedName("sprites")
    val sprites: Sprites = Sprites(),
    @SerializedName("stats")
    val stats: List<Stat> = listOf(),
    @SerializedName("types")
    val types: List<Type> = listOf(),
    @SerializedName("weight")
    val weight: Int = 0 // 69
)