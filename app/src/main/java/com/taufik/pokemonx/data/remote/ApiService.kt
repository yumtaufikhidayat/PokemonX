package com.taufik.pokemonx.data.remote

import com.taufik.pokemonx.model.detail.DetailPokemonResponse
import com.taufik.pokemonx.model.home.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(UrlEndpoint.EP_POKEMON)
    suspend fun getPokemonList(): Response<PokemonListResponse>

    @GET(UrlEndpoint.EP_POKEMON_PATH_NAME)
    suspend fun getPokemonByName(
        @Path(UrlEndpoint.QUERY_PATH_NAME) name: String
    ): Response<DetailPokemonResponse>
}