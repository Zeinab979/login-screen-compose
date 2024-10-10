package com.example.loginpage.presentation.screens.commonComponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginpage.R
import com.example.loginpage.presentation.theme.LoginPageTheme

@Composable
fun MyButton(
    onClick: () -> Unit,
    text: Int,
) {
    Button(
        onClick = { onClick() },
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyButtonPreview() {
    LoginPageTheme {
        MyButton(onClick = { }, text = R.string.sign_up)
    }

}