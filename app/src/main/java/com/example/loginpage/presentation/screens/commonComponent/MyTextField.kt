package com.example.loginpage.presentation.screens.commonComponent

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginpage.R
import com.example.loginpage.presentation.theme.LoginPageTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    keyBoardType: KeyboardType,
    placeholder: Int,
    imeAction: ImeAction = ImeAction.Done,
    ) {
    var isPassword by remember {
        mutableStateOf(keyBoardType == KeyboardType.Password)
    }
    Column {
        Text(
            text = stringResource(id = label),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 7.dp),
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            maxLines = 1,
            placeholder = {
                Text(
                    text = stringResource(id = placeholder),
                    style =MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },

            trailingIcon = if (keyBoardType == KeyboardType.Password) {
                {
                    IconButton(onClick = { isPassword = !isPassword }) {
                        Icon(
                            painter = painterResource(
                                id = if (isPassword) R.drawable.outline_visibility_off_24 else R.drawable.outline_visibility_24),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            } else null,
            visualTransformation = if (isPassword)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyBoardType,
                imeAction = imeAction
            ),
            shape = MaterialTheme.shapes.large,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                )

        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldPreview() {
    LoginPageTheme {
        MyTextField(
            value = "",
            onValueChange = { },
            label = R.string.password,
            placeholder = R.string.password,
            keyBoardType = KeyboardType.Password,
        )
    }
}

