package com.gg.loginmodule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gg.loginmodule.domain.ISignInWith
import com.gg.loginmodule.domain.models.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    private val signInWith: ISignInWith
): ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.StandBy)
    val loginUiState = _loginUiState

    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            _loginUiState.value = LoginUiState.Loading
            try {
                val userAuth = signInWith.signInWithEmail(email, password)
                if (userAuth != null) {
                    _loginUiState.value = LoginUiState.Success("Login successful")
                } else {
                    _loginUiState.value = LoginUiState.Error("Invalid credentials")
                }
            } catch (e: Exception) {
                _loginUiState.value = LoginUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun signInWithGoogle(){
        viewModelScope.launch {
            _loginUiState.value = LoginUiState.Loading
            try {
                val userAuth = signInWith.signInWithGoogle()
                if (userAuth != null) {
                    _loginUiState.value = LoginUiState.Success("Login successful")
                } else {
                    _loginUiState.value = LoginUiState.Error("Google sign-in failed")
                }
            } catch (e: Exception) {
                _loginUiState.value = LoginUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

}