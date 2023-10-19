package com.taufik.pokemonx.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taufik.pokemonx.data.PokemonXRepository
import com.taufik.pokemonx.data.remote.NetworkResult
import com.taufik.pokemonx.model.detail.DetailPokemonResponse
import com.taufik.pokemonx.model.home.PokemonListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PokemonXRepository
) : ViewModel() {

    private val _getPokemonList: MutableLiveData<NetworkResult<PokemonListResponse>> = MutableLiveData()
    val getPokemonList: LiveData<NetworkResult<PokemonListResponse>> = _getPokemonList

    private val _getPokemonByName: MutableLiveData<NetworkResult<DetailPokemonResponse>> = MutableLiveData()
    val getPokemonByName: LiveData<NetworkResult<DetailPokemonResponse>> = _getPokemonByName

    fun getPokemonList() = viewModelScope.launch {
        repository.getPokemonList().collect {
            _getPokemonList.value = it
        }
    }

    fun getPokemonByName(name: String) = viewModelScope.launch {
        repository.getPokemonByName(name).collect {
            _getPokemonByName.value = it
        }
    }
}