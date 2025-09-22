package com.gg.loginmodule.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gg.loginmodule.R

@Composable
fun SignInWithButtonComp(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    @StringRes iconDescriptionRes: Int,
    @StringRes buttonTextRes: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = stringResource(id = iconDescriptionRes),
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(stringResource(id = buttonTextRes))
        }
    }
}

@Preview
@Composable
fun SignInWithButtonCompPreview() {
    SignInWithButtonComp(
        iconRes = R.drawable.ic_launcher_background,
        iconDescriptionRes = R.string.app_name,
        buttonTextRes = R.string.app_name,
        onClick = {}
    )
}
