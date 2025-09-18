package com.gg.loginmodule.domain.models

sealed interface LoginUiState {

    object StandBy : LoginUiState
    object Loading : LoginUiState
    object Success : LoginUiState
    data class Error(
        val errorTitle: String,
        val errorMessage: String
    ) : LoginUiState
}