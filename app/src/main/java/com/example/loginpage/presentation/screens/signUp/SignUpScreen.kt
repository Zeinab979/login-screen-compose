package com.example.loginpage.presentation.screens.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loginpage.R
import com.example.loginpage.presentation.screens.commonComponent.MyButton
import com.example.loginpage.presentation.screens.commonComponent.MyTextField

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onSignUpSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf<String?>(null) }

    if (showDialog != null) {
        SignUpResultDialog(message = showDialog!!) {
            showDialog = null
        }
    }

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 18.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(17.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .padding(horizontal = 117.dp)
                .size(110.dp)
        )

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 37.sp
                    )
                ) {
                    append(stringResource(R.string.sign_up) + "\n")
                }
                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp
                    )
                ) {
                    append(stringResource(R.string.create_your_profile))
                }
            }
        )

        MyTextField(
            value = uiState.email,
            onValueChange = { viewModel.updateEmail(it) },
            label = R.string.email,
            placeholder = R.string.email,
            keyBoardType = KeyboardType.Email
        )

        MyTextField(
            value = uiState.name,
            onValueChange = { viewModel.updateName(it) },
            label = R.string.city,
            placeholder = R.string.city,
            keyBoardType = KeyboardType.Text
        )

        MyTextField(
            value = uiState.password,
            onValueChange = { viewModel.updatePassword(it) },
            label = R.string.password,
            placeholder = R.string.password,
            keyBoardType = KeyboardType.Password
        )

        var checked by remember { mutableStateOf(false) }
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .height(24.dp)
                    .width(26.dp)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    ) {
                        append(stringResource(id = R.string.by_signing_up_you_agree_to_the))
                    }
                    withStyle(
                        style = SpanStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append(stringResource(id = R.string.terms_of_service_and_privacy_policy))
                    }
                },
                modifier = Modifier.clickable {
                }
            )
        }

        MyButton(
            onClick = {
                if ( uiState.password.length >= 6) {
                    viewModel.signUp()
                } else {
                    showDialog = stringResource(R.string.password_length_message)
                }
            },
            text = R.string.sign_up
        )

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    append(stringResource(id = R.string.already_have_an_account))
                }
                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(stringResource(id = R.string.login))
                }
            },
            modifier = Modifier
                .padding(horizontal = 50.dp)
                .clickable { onNavigateToLogin() }
        )
    }
}

@Composable
fun SignUpResultDialog(message: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Sign Up Result") },
        text = { Text(message) },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}