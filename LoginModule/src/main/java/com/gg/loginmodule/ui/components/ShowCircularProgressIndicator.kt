package com.gg.loginmodule.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog

@Composable
fun ShowCircularProgressIndicator(
    showLoading: Boolean,
) {
    if (showLoading) {
        Dialog(onDismissRequest = {}) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
//                    color = colorResource(R.color.primary),
//                    trackColor = colorResource(R.color.cream)
                )
            }
        }
    }
}

@Preview
@Composable
fun ShowCircularProgressIndicatorPreview() {
    ShowCircularProgressIndicator(showLoading = true)
}
