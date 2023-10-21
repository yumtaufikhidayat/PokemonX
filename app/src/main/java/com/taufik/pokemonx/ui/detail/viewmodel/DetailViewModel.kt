package com.taufik.pokemonx.ui.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taufik.pokemonx.data.PokemonXRepository
import com.taufik.pokemonx.data.remote.NetworkResult
import com.taufik.pokemonx.model.detail.DetailPokemonResponse
import com.taufik.pokemonx.model.species.DetailPokemonSpeciesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokemonXRepository
) : ViewModel() {

    private val _getPokemonByName: MutableLiveData<NetworkResult<DetailPokemonResponse>> = MutableLiveData()
    val getPokemonByName: LiveData<NetworkResult<DetailPokemonResponse>> = _getPokemonByName

    private val _getPokemonSpecies: MutableLiveData<NetworkResult<DetailPokemonSpeciesResponse>> = MutableLiveData()
    val getPokemonSpecies: LiveData<NetworkResult<DetailPokemonSpeciesResponse>> = _getPokemonSpecies

    fun getPokemonByName(name: String) = viewModelScope.launch {
        repository.getPokemonByName(name).collect {
            _getPokemonByName.value = it
        }
    }

    fun getPokemonSpecies(name: String) = viewModelScope.launch {
        repository.getPokemonSpecies(name).collect {
            _getPokemonSpecies.value = it
        }
    }
}