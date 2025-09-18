package com.gg.loginmodule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gg.loginmodule.domain.ISignInWith
import com.gg.loginmodule.domain.models.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    private val signInWith: ISignInWith
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.StandBy)
    val loginUiState = _loginUiState

    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            _loginUiState.value = LoginUiState.Loading
            try {
                val userAuth = signInWith.signInWithEmail(email, password)
                if (userAuth != null) {
                    _loginUiState.value = LoginUiState.Success
                } else {
                    _loginUiState.value = LoginUiState.Error(
                        "Invalid credentials",
                        "Please check your email and password and try again.",
                    )
                }
            } catch (e: Exception) {
                _loginUiState.value = LoginUiState.Error(
                    e.message ?: "Unknown error",
                    "An unexpected error occurred. Please try again later.",
                )
            }
        }
    }

    fun signInWithGoogle() {
        viewModelScope.launch {
            _loginUiState.value = LoginUiState.Loading
            try {
                val userAuth = signInWith.signInWithGoogle()
                if (userAuth != null) {
                    _loginUiState.value = LoginUiState.Success
                } else {
                    _loginUiState.value = LoginUiState.Error(
                        "Google sign-in failed",
                        " Unable to authenticate with Google. Please try again."
                    )
                }
            } catch (e: Exception) {
                _loginUiState.value = LoginUiState.Error(
                    e.message ?: "Unknown error",
                    "An unexpected error occurred. Please try again later."
                )
            }
        }
    }

}