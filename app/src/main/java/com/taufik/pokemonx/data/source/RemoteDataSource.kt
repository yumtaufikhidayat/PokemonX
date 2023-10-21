package com.taufik.pokemonx.data.source

import com.taufik.pokemonx.data.remote.ApiService
import com.taufik.pokemonx.data.remote.BaseApiResponse
import com.taufik.pokemonx.data.remote.NetworkResult
import com.taufik.pokemonx.model.detail.DetailPokemonResponse
import com.taufik.pokemonx.model.home.PokemonListResponse
import com.taufik.pokemonx.model.species.DetailPokemonSpeciesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : BaseApiResponse() {

    private val dispatchersIO = Dispatchers.IO

    fun getPokemonList(): Flow<NetworkResult<PokemonListResponse>> {
        return flow {
            emit(safeApiCall { apiService.getPokemonList() })
        }.flowOn(dispatchersIO)
    }

    fun getPokemonByName(name: String): Flow<NetworkResult<DetailPokemonResponse>> {
        return flow {
            emit(safeApiCall { apiService.getPokemonByName(name) })
        }.flowOn(dispatchersIO)
    }

    fun getPokemonSpecies(name: String): Flow<NetworkResult<DetailPokemonSpeciesResponse>> {
        return flow {
            emit(safeApiCall { apiService.getPokemonSpecies(name) })
        }.flowOn(dispatchersIO)
    }


}