package com.example.loginpage.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginpage.R
import com.example.loginpage.presentation.screens.commonComponent.MyButton
import com.example.loginpage.presentation.theme.LoginPageTheme

@Composable
fun ComingSoon(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(204.dp,108.dp)
        )
       Text(
           text = stringResource(R.string.coming_soon),
           style = MaterialTheme.typography.displaySmall,
           modifier = Modifier.padding(vertical = 20.dp)
       )
     MyButton(onClick = { }, text = R.string.refresh)
    }

}

@Preview(showBackground = true)
@Composable
private fun ComingSoonPreview() {
    LoginPageTheme {
        ComingSoon()
    }
}