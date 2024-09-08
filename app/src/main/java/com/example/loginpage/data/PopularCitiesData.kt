package com.example.loginpage.data


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.loginpage.R

data class PopularCitiesData(
    @DrawableRes val cityImage: Int,
    @StringRes val cityName: Int,
    @StringRes val review: Int,
    @StringRes val details: Int,

    )

val popularCities = listOf(
    PopularCitiesData(
        cityImage = R.drawable.city1,
        cityName = R.string.coeurdes_alpes,
        review = R.string._355_reviews,
        details = R.string.details
    ),
    PopularCitiesData(
        cityImage = R.drawable.city,
        cityName = R.string.coeurdes_alpes,
        review = R.string._355_reviews,
        details = R.string.details
    ),
    PopularCitiesData(
        cityImage = R.drawable.city3,
        cityName = R.string.coeurdes_alpes,
        review = R.string._355_reviews,
        details = R.string.details
    )
)
