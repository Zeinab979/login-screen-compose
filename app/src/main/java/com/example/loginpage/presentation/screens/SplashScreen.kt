package com.example.loginpage.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginpage.R
import com.example.loginpage.presentation.screens.commonComponent.MyButton
import com.example.loginpage.presentation.theme.LoginPageTheme

@Composable
fun SplashScreen(
    navigateTo: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "back ground",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 37.dp, vertical = 57.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = stringResource(R.string.aspen),
                fontFamily = FontFamily(
                    Font(R.font.hiatus1),
                ),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 130.sp,
                modifier = Modifier.padding(start = 26.dp, end = 26.dp, top = 60.dp)
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 27.sp,
                            color = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        append(stringResource(id = R.string.plan_your) + "\n\n")
                    }
                    withStyle(

                        style = SpanStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 47.sp,
                            color = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        append(stringResource(id = R.string.luxurious_vacation) + "\n\n")
                    }
                }
            )
            MyButton(
                text = R.string.explore,
                onClick = { navigateTo() })
            Spacer(modifier = Modifier.height(30.dp))

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    LoginPageTheme {
        SplashScreen() {}
    }
}
