package com.taufik.pokemonx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PokemonXDb : RoomDatabase() {
    abstract fun getPokemonXDao(): PokemonXDao
}