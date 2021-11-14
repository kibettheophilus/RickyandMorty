package dev.kibet.domain.models.state

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: Any) : UiState()
    data class Error(val error: String) : UiState()
}
