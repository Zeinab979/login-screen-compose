package com.example.loginpage.presentation.screens.popular_cities_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginpage.R
import com.example.loginpage.data.PopularCitiesData
import com.example.loginpage.presentation.theme.LoginPageTheme

@Composable
fun ItemDetails(
    uiState: PopularCitiesData,
    onItemClicked: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(bottom = 48.dp)
            .height(180.dp)
            .clickable { onItemClicked() }

    ) {

        Image(
            bitmap = ImageBitmap.imageResource(uiState.cityImage),
            contentDescription = "city",
            modifier = Modifier
                .size(210.dp, 177.dp)
                .shadow(
                    elevation = 35.dp,
                    shape = MaterialTheme.shapes.large,
                    clip = true,
                    spotColor = MaterialTheme.colorScheme.primary,
                    ambientColor = MaterialTheme.colorScheme.primary,
                )
        )
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                    )

                ) {
                    append(stringResource(uiState.cityName) + "\n")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    append("        " + stringResource(uiState.review))
                }
            },
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}

@Preview
@Composable
private fun ComingSoonItemPreview() {
    LoginPageTheme {
        ItemDetails(
            uiState = PopularCitiesData(
                cityImage = R.drawable.city,
                cityName = R.string.coeurdes_alpes,
                review = R.string._355_reviews,
                details = R.string.details
            )
        ) {}
    }
}