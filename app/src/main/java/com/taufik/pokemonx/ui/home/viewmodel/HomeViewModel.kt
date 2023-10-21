package com.taufik.pokemonx.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taufik.pokemonx.data.PokemonXRepository
import com.taufik.pokemonx.data.local.PokemonEntity
import com.taufik.pokemonx.data.remote.NetworkResult
import com.taufik.pokemonx.model.home.PokemonListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PokemonXRepository
) : ViewModel() {

    private val _getPokemonList: MutableLiveData<NetworkResult<PokemonListResponse>> = MutableLiveData()
    val getPokemonList: LiveData<NetworkResult<PokemonListResponse>> = _getPokemonList

    fun getPokemonList() = viewModelScope.launch {
        repository.getPokemonList().collect {
            _getPokemonList.value = it
        }
    }

    fun savePokemonList(
        id: Int,
        name: String,
        url: String,
        imageUrl: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.savePokemonList(
                PokemonEntity(id, name, url, imageUrl)
            )
        }
    }
}