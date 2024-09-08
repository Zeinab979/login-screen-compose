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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginpage.R
import com.example.loginpage.presentation.screens.commonComponent.MyButton
import com.example.loginpage.presentation.screens.commonComponent.MyTextField
import com.example.loginpage.presentation.theme.LoginPageTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navigateTo : ()->Unit
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
                    append(stringResource(R.string.sign_up) + "\n")
                }
                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,

                        )
                ) {
                    append(stringResource(R.string.create_your_profile))
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
        var email by remember {
            mutableStateOf("")
        }
        MyTextField(
            value = email,
            onValueChange = { newEmail ->
                email = newEmail
            },
            label = R.string.email,
            placeholder = R.string.email,
            keyBoardType = KeyboardType.Email,
        )
        var city by remember {
            mutableStateOf("")
        }
        MyTextField(
            value = city,
            onValueChange = { newCity ->
                city = newCity
            },
            label = R.string.city,
            placeholder = R.string.city,
            keyBoardType = KeyboardType.Text,
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
        var checked by remember {
            mutableStateOf(false)
        }
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
                    uncheckedColor = MaterialTheme.colorScheme.primary,
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
                            fontSize = 14.sp,
                        )

                    ) {
                        append(stringResource(id = R.string.by_signing_up_you_agree_to_the))

                    }
                    withStyle(
                        style = SpanStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary,

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
            onClick = { },
            text = R.string.sign_up,
        )
        Text(text = buildAnnotatedString {
            withStyle(
               style = SpanStyle(
                   fontFamily = FontFamily.Default,
                   fontWeight = FontWeight.Medium,
                   fontSize = 16.sp,
                   color = MaterialTheme.colorScheme.onSecondary
               )
            ){
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
            ){
                append(stringResource(id = R.string.login))
            }

        },
            modifier = Modifier
                .padding(horizontal = 50.dp)
                .clickable { navigateTo()}
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    LoginPageTheme {
        SignUpScreen(
        ){}
    }

}