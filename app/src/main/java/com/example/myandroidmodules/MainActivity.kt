package com.example.myandroidmodules

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.myandroidmodules.ui.theme.MyAndroidModulesTheme
import com.gg.loginmodule.ui.CreateAccount

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAndroidModulesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    LoginScreen(
//                        modifier = Modifier,
//                        title = "Login",
//                        loginUiState = LoginUiState.StandBy,
//                        onClickSignInWithEmail = { _, _ -> },
//                        onClickSignInWithGoogle = {},
//                        onNavigateHome = {},
//                        onNavigateToForgotPassword = {}
//                    )
                    CreateAccount(
                        onNavigateBack = {},
                        onSignUp = {},
                        onSignInWithGoogle = {},
                    )
                }
            }
        }
    }
}