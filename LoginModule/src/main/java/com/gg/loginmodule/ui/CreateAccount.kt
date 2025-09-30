package com.gg.loginmodule.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.gg.loginmodule.BuildConfig
import com.gg.loginmodule.R
import com.gg.loginmodule.domain.models.LoginUiState
import com.gg.loginmodule.ui.components.LoginAlertDialog
import com.gg.loginmodule.ui.components.ShowCircularProgressIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccount(
    // Rule 5: modifier as first param
    modifier: Modifier = Modifier,
    loginUiState: LoginUiState,
    onNavigateBack: () -> Unit,
    onNavigateHome: () -> Unit,
    onSignUpWithEmail: (String, String, String) -> Unit,
    onSignInWithGoogle: () -> Unit,
) {
    var username by remember { mutableStateOf(BuildConfig.NAME) }
    var email by remember { mutableStateOf(BuildConfig.EMAIL) }
    var password by remember { mutableStateOf(BuildConfig.PASSWORD) }
    var confirmPassword by remember { mutableStateOf(BuildConfig.RE_PASSWORD) }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var selectedImage: String? by remember { mutableStateOf(null) }
    var isImageError by remember { mutableStateOf(false) }

    val selectedImagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {
            selectedImage = it.toString()
            isImageError = false
        }
    }

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

    // Scaffold for overall screen structure
    Scaffold(
        modifier = Modifier, // Apply modifier to Scaffold
        topBar = {
            // TopAppBar------------------------------
            CenterAlignedTopAppBar(
                title = { Text(stringResource(id = R.string.create_account_top_bar_title)) },
                navigationIcon = {
                    // IconButton Back------------------------------
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.create_account_back_button_description)
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp) // Adjusted horizontal padding
                .verticalScroll(rememberScrollState()), // Make column scrollable
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(24.dp)) // Space from TopAppBar

            // Profile Image Avatar with Camera Icon------------------------------
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clickable(
                        onClick = {
                            selectedImagePicker.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                    )
            ) {
                // Image Avatar------------------------------
                Image(
                    painter = if (selectedImage != null) rememberAsyncImagePainter(selectedImage) else painterResource(
                        id = R.drawable.person
                    ),
                    contentDescription = stringResource(id = R.string.create_account_avatar_content_description),
                    modifier = Modifier
                        .fillMaxSize()
                        .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        .padding(1.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                // IconButton Camera------------------------------
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(4.dp)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_a_photo),
                        contentDescription = stringResource(id = R.string.create_account_camera_icon_content_description),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            //Error image
            if (isImageError) {
                Text(
                    text = "Por favor, selecciona una imagen",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }


            Spacer(modifier = Modifier.height(32.dp)) // Space after avatar

            // OutlinedTextField Username------------------------------
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(stringResource(id = R.string.create_account_username_label)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null // Decorative
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // OutlinedTextField Email------------------------------
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(id = R.string.create_account_email_label)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = null // Decorative
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // OutlinedTextField Password------------------------------
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(id = R.string.create_account_password_label)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null // Decorative
                    )
                },
                trailingIcon = {
                    val image =
                        if (passwordVisible) R.drawable.visibility else R.drawable.visibility_off
                    val description =
                        if (passwordVisible) stringResource(R.string.create_account_hide_password) else stringResource(
                            R.string.create_account_show_password
                        )
                    // IconButton Toggle Password Visibility------------------------------
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(id = image),
                            contentDescription = description
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // OutlinedTextField Confirm Password------------------------------
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(stringResource(id = R.string.create_account_confirm_password_label)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null // Decorative
                    )
                },
                trailingIcon = {
                    val image =
                        if (confirmPasswordVisible) R.drawable.visibility else R.drawable.visibility_off
                    val description =
                        if (confirmPasswordVisible) stringResource(R.string.create_account_hide_password) else stringResource(
                            R.string.create_account_show_password
                        )
                    // IconButton Toggle Confirm Password Visibility------------------------------
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(
                            painter = painterResource(id = image),
                            contentDescription = description
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Button Sign Up------------------------------
            Button(
                onClick = {
                    onSignUpWithEmail(username,email,password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(stringResource(id = R.string.create_account_button_text))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Row Or Continue With------------------------------
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Divider Left------------------------------
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                )
                // Text Or Continue With------------------------------
                Text(
                    text = stringResource(id = R.string.create_account_or_continue_with_text),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                // Divider Right------------------------------
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // IconButton Sign In With Google------------------------------
            IconButton(
                onClick = {
                    onSignInWithGoogle()
                },
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(
                        2.dp,
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                        CircleShape
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = stringResource(id = R.string.create_account_google_icon_description),
                    modifier = Modifier.fillMaxSize(0.7f),
                    tint = Color.Unspecified // To use original colors of the SVG
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateAccountPreview() {
    MaterialTheme {
        CreateAccount(
            loginUiState = LoginUiState.StandBy,
            onNavigateHome = {},
            onNavigateBack = {},
            onSignUpWithEmail = { _, _, _ -> },
            onSignInWithGoogle = {},
        )
    }
}