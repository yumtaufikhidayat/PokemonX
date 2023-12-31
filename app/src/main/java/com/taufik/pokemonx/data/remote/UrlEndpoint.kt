package com.taufik.pokemonx.data.remote

import com.taufik.pokemonx.BuildConfig

object UrlEndpoint {
    const val BASE_URL = BuildConfig.BASE_URL
    const val EP_POKEMON = "pokemon"
    const val EP_POKEMON_PATH_NAME = "pokemon/{name}"
    const val EP_POKEMON_SPECIES_NAME = "pokemon-species/{name}"
    const val QUERY_PATH_NAME = "name"
}