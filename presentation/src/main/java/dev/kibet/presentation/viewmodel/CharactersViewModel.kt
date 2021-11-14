package dev.kibet.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kibet.domain.models.state.UiState
import dev.kibet.domain.usecases.FetchCharacters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

class CharactersViewModel(
    private val fetchCharacters: FetchCharacters
) : ViewModel() {
    private val _fetchCharactersStatus = MutableStateFlow<UiState>(UiState.Loading)
    val fetchCharactersStatus: StateFlow<UiState> = _fetchCharactersStatus

    init {
        getCharacters()
    }
    private fun getCharacters() {
        viewModelScope.launch {
            try {
                fetchCharacters().collect { characters ->
                    _fetchCharactersStatus.value = UiState.Success(characters)
                }
            } catch (e: Exception) {
                _fetchCharactersStatus.value =
                    UiState.Error(e.localizedMessage ?: "Problem Connecting to internet")
            } catch (e: IOException) {
                _fetchCharactersStatus.value =
                    UiState.Error(e.localizedMessage ?: "Unknown error occured")
            }
        }
    }
}
