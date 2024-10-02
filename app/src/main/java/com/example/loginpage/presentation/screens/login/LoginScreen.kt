package com.example.loginpage.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.loginpage.data.model.LoginResponse
import com.example.loginpage.presentation.screens.commonComponent.MyButton
import com.example.loginpage.presentation.screens.commonComponent.MyTextField
import com.example.loginpage.util.DataState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    uiStateFlow: StateFlow<LoginUiState>,
    loginStateFlow: StateFlow<DataState<LoginResponse>>,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by uiStateFlow.collectAsStateWithLifecycle()
    val loginState by loginStateFlow.collectAsStateWithLifecycle()

    var showDialog by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(loginState) {
        when (loginState) {
            is DataState.Loading -> {

            }

            is DataState.Success -> {
                showDialog = "Login Successful"
                onLoginSuccess()
            }

            is DataState.Error -> {
                showDialog = (loginState as DataState.Error).message
            }

            else -> {}
        }
    }
    if (showDialog != null) {
        LoginResultDialog(message = showDialog!!) {
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
                    append(stringResource(R.string.login) + "\n")
                }
                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp
                    )
                ) {
                    append(stringResource(R.string.enter_to_your_personal_profile_com_and_follow))
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
            value = uiState.password,
            onValueChange = { viewModel.updatePassword(it) },
            label = R.string.password,
            placeholder = R.string.password,
            keyBoardType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.height(22.dp))

        MyButton(
            onClick = {
                viewModel.login()
                      },
            text = R.string.login
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
                    append(stringResource(id = R.string.don_t_have_an_account_yet))
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
                    append(stringResource(id = R.string.sign_up))
                }
            },
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 30.dp)
                .clickable { onNavigateToSignUp() }
        )
    }
}

@Composable
fun LoginResultDialog(message: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Login Result") },
        text = { Text(message) },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}