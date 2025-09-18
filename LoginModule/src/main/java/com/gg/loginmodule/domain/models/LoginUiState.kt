package com.gg.loginmodule.domain.models

sealed interface LoginUiState {

    object StandBy : LoginUiState
    object Loading : LoginUiState
    data class Success(val message: String) : LoginUiState
    data class Error(val error: String) : LoginUiState

}