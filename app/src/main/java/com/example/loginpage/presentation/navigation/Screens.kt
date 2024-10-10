package com.example.loginpage.presentation.navigation

enum class Screens {
    StartScreen,
    SignUp,
    Login,
    PopularList,
    Details,

}

sealed class NavigationItem(val route: String) {
    data object StartScreen : NavigationItem(Screens.StartScreen.name)
    data object SignUp : NavigationItem(Screens.SignUp.name)
    data object Login : NavigationItem(Screens.Login.name)
    data object PopularList : NavigationItem(Screens.PopularList.name)
    data object Details : NavigationItem(Screens.Details.name){
        fun createRoute(productId: Int): String = "$route/$productId"
    }

}
