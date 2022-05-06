package dev.kibet.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kibet.domain.models.Character
import dev.kibet.domain.models.state.UiState
import dev.kibet.domain.usecases.FetchCharacters
import dev.kibet.domain.usecases.FetchSingleCharacter
import dev.kibet.domain.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

class CharactersViewModel(
    private val fetchCharacters: FetchCharacters,
    private val fetchSingleCharacter: FetchSingleCharacter
) : ViewModel() {
     val mutablefetchCharactersStatus = MutableStateFlow<UiState>(UiState.Loading)
    val fetchCharactersStatus: StateFlow<UiState> = mutablefetchCharactersStatus

    private val _fetchSingleCharacterStatus = MutableLiveData<Resource<Character>>()
    val fetchSingleCharacterStatus: LiveData<Resource<Character>> = _fetchSingleCharacterStatus

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            try {
                fetchCharacters().collect { characters ->
                    mutablefetchCharactersStatus.value = UiState.Success(characters)
                }
            } catch (e: Exception) {
                mutablefetchCharactersStatus.value =
                    UiState.Error(e.localizedMessage ?: "Problem Connecting to internet")
            } catch (e: IOException) {
                mutablefetchCharactersStatus.value =
                    UiState.Error(e.localizedMessage ?: "Unknown error occured")
            }
        }
    }

    fun getSingleCharacter(id: Int) {
        viewModelScope.launch {
            try {
                fetchSingleCharacter(id).collect { character ->
                    _fetchSingleCharacterStatus.value = Resource.success(character)
                }
            } catch (e: Exception) {
                _fetchSingleCharacterStatus.value =
                    Resource.error(e.localizedMessage ?: "Problem Connecting to internet", null)
            } catch (e: IOException) {
                _fetchSingleCharacterStatus.value =
                    Resource.error(e.localizedMessage ?: "Unknown error occured", null)
            }
        }
    }
}
