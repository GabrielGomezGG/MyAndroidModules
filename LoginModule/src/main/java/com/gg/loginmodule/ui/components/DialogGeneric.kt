package com.gg.loginmodule.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewLightDark

@Composable
fun DialogGeneric(
    showDialog: Boolean,
    title: String,
    body: String,
    dismissButtonText: String = "Cancelar",
    icon: ImageVector? = Icons.Default.Warning,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {

    AnimatedVisibility (showDialog) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            icon = {
                icon?.let {Icon(imageVector = icon, contentDescription = "icon dialog") }
            },
            title = {
                Text(title)
            },
            text = {
                Text(body)
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(dismissButtonText)
                }
            },
            confirmButton = {
                Button(onClick = onConfirm) {
                    Text("Confirmar")
                }
            },
        )
    }
}

@PreviewLightDark
//@PreviewFontScale
//@PreviewScreenSizes
@Composable
private fun DialogWithTextFieldPreview() {
//    Burger23Theme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
            DialogGeneric(
                title = "Title",
                body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                showDialog = true,
                icon = Icons.Default.Warning,
                onDismissRequest = {},
                onConfirm = {}
            )
//        }
//    }
}