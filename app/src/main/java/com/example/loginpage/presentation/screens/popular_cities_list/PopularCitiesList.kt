package com.example.loginpage.presentation.screens.popular_cities_list

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.loginpage.data.popularCities
import com.example.loginpage.presentation.theme.LoginPageTheme

@Composable
fun PopularCitiesList(
    onItemClicked : (Int) -> Unit
) {

     LazyColumn {
         item { Column (
             horizontalAlignment = Alignment.Start,
             modifier = Modifier
                 .fillMaxSize()
                 .padding(start = 14.dp),

             ){
             Image(
                 painter = painterResource(id = R.drawable.logo),
                 contentDescription = "logo",
                 modifier = Modifier
                     .padding(horizontal = 117.dp, vertical = 34.dp)
                     .size(135.dp, 116.dp)

             )
             Text(
                 text = stringResource(id = R.string.popular_cities),
                 style = MaterialTheme.typography.headlineMedium,
                 modifier = Modifier.padding( bottom = 19.dp)
             )} }
         itemsIndexed(popularCities) { index , item ->
             ItemDetails(
                 uiState = item,
                 onItemClicked = {
                     Log.d("PopularCitiesList", "Item clicked with index: $index")
                     onItemClicked(index)
                 }
             )
         }
     }

}
@Preview(showBackground = true)
@Composable
private fun ComingSoonListPreview() {
    LoginPageTheme {
        PopularCitiesList(onItemClicked = {})
    }
}