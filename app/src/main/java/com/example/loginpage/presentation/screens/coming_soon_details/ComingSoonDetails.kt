package com.example.loginpage.presentation.screens.coming_soon_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.loginpage.R
import com.example.loginpage.data.model.Product

@Composable
fun ComingSoonDetails(
    product: Product,
    onBackClick: () -> Unit,
) {

    Scaffold(
        topBar = {
            ScreenAppBar(onBackClick = onBackClick)
        },
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(start = 27.dp, end = 27.dp)
    ) {

        Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = product.image),
                        contentDescription = null,
                        modifier = Modifier
                            .height(267.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = product.price.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAppBar(
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = { },
        navigationIcon = {
            Box(
                modifier = Modifier
                    .size(44.dp, 43.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(8.dp)
                    .clickable { onBackClick() }

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = "back arrow",
                    tint = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.size(24.dp)

                )
            }
        }
    )

}

