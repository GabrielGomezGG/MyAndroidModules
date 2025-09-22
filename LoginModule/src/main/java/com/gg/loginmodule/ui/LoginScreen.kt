package com.gg.loginmodule.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gg.loginmodule.R
import com.gg.loginmodule.domain.models.LoginUiState
import com.gg.loginmodule.ui.components.LoginAlertDialog
import com.gg.loginmodule.ui.components.ShowCircularProgressIndicator
import com.gg.loginmodule.ui.components.SignInWithButtonComp


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    title: String,
    loginUiState: LoginUiState,
    onClickSignInWithEmail: (String, String) -> Unit,
    onClickSignInWithGoogle: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(true) }

    when (loginUiState) {
        LoginUiState.StandBy -> {}
        LoginUiState.Loading -> {
            ShowCircularProgressIndicator(true)
        }
        is LoginUiState.Success -> onNavigateHome()
        is LoginUiState.Error -> {
            LoginAlertDialog(
                modifier = Modifier,
                showDialog = showDialog,
                icon = painterResource(R.drawable.error),
                dialogTitle = loginUiState.errorTitle,
                dialogText = loginUiState.errorMessage,
                onDismissRequest = { showDialog = false },
                onConfirmation = { showDialog = false }
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo ------------------------------------------------------------------------------------
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Uses LoginModule's R
            contentDescription = stringResource(id = R.string.login_logo_content_description),
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // Title -------------------------------------------------------------------------------
            Text(text = title, style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Email and Password TextFields -------------------------------------------------------
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(id = R.string.login_username_label)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(id = R.string.login_password_label)) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (passwordVisible) R.drawable.visibility else R.drawable.visibility_off

                    val description = if (passwordVisible)
                        stringResource(id = R.string.login_hide_password_description)
                    else
                        stringResource(id = R.string.login_show_password_description)

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(id = image),
                            contentDescription = description
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sign In With Email Button -----------------------------------------------------------
            Button(
                onClick = { onClickSignInWithEmail(email, password) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.login_button_text))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot Password Button --------------------------------------------------------------
            TextButton(onClick = { onNavigateToForgotPassword() }) {
                Text(stringResource(id = R.string.login_forgot_password_button_text))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    thickness = DividerDefaults.Thickness,
                    color = DividerDefaults.color
                )
                Text(
                    stringResource(id = R.string.login_or_continue_with),
                    style = MaterialTheme.typography.bodySmall
                )
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    thickness = DividerDefaults.Thickness,
                    color = DividerDefaults.color
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sign In With Google Button ----------------------------------------------------------
            SignInWithButtonComp(
                modifier = Modifier.fillMaxWidth(),
                iconRes = R.drawable.google_icon,
                iconDescriptionRes = R.string.login_google_icon_content_description,
                buttonTextRes = R.string.login_sign_in_with_google_button_text,
                onClick = { onClickSignInWithGoogle() }
            )
        }
    }
}

@Preview
//@PreviewLightDark
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        modifier = Modifier,
        title = "Login",
        loginUiState = LoginUiState.StandBy,
        onClickSignInWithEmail = { _, _ -> },
        onClickSignInWithGoogle = {},
        onNavigateHome = {},
        onNavigateToForgotPassword = {}
    )
}
