package com.taufik.pokemonx.data

import com.taufik.pokemonx.data.source.RemoteDataSource
import javax.inject.Inject

class PokemonXRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun getPokemonList() = remoteDataSource.getPokemonList()

    fun getPokemonByName(name: String) = remoteDataSource.getPokemonByName(name)
}