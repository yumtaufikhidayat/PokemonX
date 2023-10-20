package com.taufik.pokemonx.data.source

import com.taufik.pokemonx.data.local.PokemonEntity
import com.taufik.pokemonx.data.local.PokemonXDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: PokemonXDao
) {
    fun savePokemonList(pokemonEntity: PokemonEntity) = dao.savePokemonList(pokemonEntity)
}