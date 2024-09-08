package com.example.loginpage.presentation.navigation

enum class Screens {
    SplashScreen,
    SignUp,
    Login,
    PopularCitiesList,
    CityDetails,

}

sealed class NavigationItem(val route: String) {
    data object SplashScreen : NavigationItem(Screens.SplashScreen.name)
    data object SignUp : NavigationItem(Screens.SignUp.name)
    data object Login : NavigationItem(Screens.Login.name)
    data object PopularCitiesList : NavigationItem(Screens.PopularCitiesList.name)
    data object CityDetails : NavigationItem(Screens.CityDetails.name)

}
