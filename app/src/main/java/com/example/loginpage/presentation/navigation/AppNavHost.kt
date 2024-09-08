package com.example.loginpage.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.loginpage.data.popularCities
import com.example.loginpage.presentation.screens.SplashScreen
import com.example.loginpage.presentation.screens.coming_soon_details.ComingSoonDetails
import com.example.loginpage.presentation.screens.login.LoginScreen
import com.example.loginpage.presentation.screens.popular_cities_list.PopularCitiesList
import com.example.loginpage.presentation.screens.signUp.SignUpScreen

import android.util.Log

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = NavigationItem.SplashScreen.route,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.SplashScreen.route) {
            SplashScreen(
                navigateTo = { navController.navigate(NavigationItem.SignUp.route) }
            )
        }
        composable(NavigationItem.SignUp.route) {
            SignUpScreen(navigateTo = { navController.navigate(NavigationItem.Login.route) })
        }
        composable(NavigationItem.Login.route) {
            LoginScreen(
                navigateTo = { navController.navigate(NavigationItem.PopularCitiesList.route) },
                navigateUp = { navController.navigateUp() }
            )
        }
        composable(NavigationItem.PopularCitiesList.route) {
            PopularCitiesList(
                onItemClicked = { index ->
                    Log.d("NavHost", "Navigating to CityDetails with index: $index")
                    navController.navigate("${NavigationItem.CityDetails.route}/$index")
                }
            )
        }
        composable(
            route = "${NavigationItem.CityDetails.route}/{index}",
            arguments = listOf(navArgument("index") { NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: -1
            Log.d("NavHost", "CityDetails received index: $index")

            if (index in popularCities.indices) {
                val city = popularCities[index]
                Log.d("NavHost", "CityDetails displaying: ${city.cityName}")

                ComingSoonDetails(
                    cityDetails = city,
                    onBackClick = { navController.navigateUp() }
                )
            } else {
                Log.e("NavHost", "Invalid city index: $index")
            }
        }

    }
}


