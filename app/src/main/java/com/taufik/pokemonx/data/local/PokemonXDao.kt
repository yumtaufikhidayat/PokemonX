package com.taufik.pokemonx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface PokemonXDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePokemonList(pokemonEntity: PokemonEntity)
}