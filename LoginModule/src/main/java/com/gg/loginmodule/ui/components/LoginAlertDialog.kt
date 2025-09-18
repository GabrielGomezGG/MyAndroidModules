package com.gg.loginmodule.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gg.loginmodule.R

@Composable
fun LoginAlertDialog(
    modifier: Modifier = Modifier,
    showDialog: Boolean = true,
    icon: Painter,
    dialogTitle: String,
    dialogText: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    if(!showDialog) return
    AlertDialog(
        modifier = modifier,
        icon = {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
            )
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            OutlinedButton(
                modifier = Modifier,
                content = { Text(text = "Confirmar") },
                onClick = { onConfirmation() }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun LoginAlertDialogPreview(
    modifier: Modifier = Modifier
) {
    LoginAlertDialog(
        modifier = modifier,
        showDialog = true,
        icon = painterResource(R.drawable.error),
        dialogTitle = "Title",
        dialogText = "This is a sample alert dialog message.",
        onDismissRequest = {},
        onConfirmation = {}
    )
}