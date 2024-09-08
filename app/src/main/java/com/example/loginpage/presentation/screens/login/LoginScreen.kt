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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginpage.R
import com.example.loginpage.presentation.screens.commonComponent.MyButton
import com.example.loginpage.presentation.screens.commonComponent.MyTextField
import com.example.loginpage.presentation.theme.LoginPageTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateTo : ()->Unit,
    navigateUp : () ->Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(17.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 18.dp)
            .fillMaxSize()

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
                        fontSize = 37.sp,
                    )

                ) {
                    append(stringResource(R.string.login) + "\n")
                }
                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,

                        )
                ) {
                    append(stringResource(R.string.enter_to_your_personal_profile_com_and_follow))
                }

            },
        )
        var phoneNumber by remember {
            mutableStateOf("")
        }
        MyTextField(
            value = phoneNumber,
            onValueChange = { newPhoneNumber ->
                phoneNumber = newPhoneNumber
            },
            label = R.string.phone_number,
            placeholder = R.string.phone_number,
            keyBoardType = KeyboardType.Number,
        )
        var password by remember {
            mutableStateOf("")
        }
        MyTextField(
            value = password,
            onValueChange = { newPassword ->
                password = newPassword
            },
            label = R.string.password,
            placeholder = R.string.password,
            keyBoardType = KeyboardType.Password,
        )
        Spacer(modifier = Modifier.height(22.dp))
        MyButton(
            onClick = { navigateTo()},
            text = R.string.login,
        )
        Text(text = buildAnnotatedString {
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
                .clickable { navigateUp()}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    LoginPageTheme {
        LoginScreen(
            navigateTo = {}
        ){}
    }

}