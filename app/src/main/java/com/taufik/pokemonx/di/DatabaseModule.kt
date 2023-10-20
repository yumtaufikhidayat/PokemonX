package com.taufik.pokemonx.di

import android.content.Context
import androidx.room.Room
import com.taufik.pokemonx.data.local.PokemonXDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext mContext: Context
    ) = Room.databaseBuilder(
        mContext,
        PokemonXDb::class.java,
        "PokemonX.db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideDao(database: PokemonXDb) = database.getPokemonXDao()
}