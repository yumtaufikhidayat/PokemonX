package com.taufik.pokemonx.data

import com.taufik.pokemonx.data.local.PokemonEntity
import com.taufik.pokemonx.data.source.LocalDataSource
import com.taufik.pokemonx.data.source.RemoteDataSource
import javax.inject.Inject

class PokemonXRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    fun getPokemonList() = remoteDataSource.getPokemonList()

    fun getPokemonByName(name: String) = remoteDataSource.getPokemonByName(name)

    fun savePokemonList(pokemonEntity: PokemonEntity) = localDataSource.savePokemonList(pokemonEntity)
}