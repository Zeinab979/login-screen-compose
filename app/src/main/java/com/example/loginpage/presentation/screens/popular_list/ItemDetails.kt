package com.example.loginpage.presentation.screens.popular_list

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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.loginpage.data.model.Product

@Composable
fun ItemDetails(
    uiState: Product,
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
            painter = rememberAsyncImagePainter(model = uiState.image),
            contentDescription = "city",
            modifier = Modifier
                .size(210.dp, 177.dp)
                .shadow(
                    elevation = 10.dp,
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
                    append(uiState.name + "\n")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    append("        $${uiState.price}")
                }
            },
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}
