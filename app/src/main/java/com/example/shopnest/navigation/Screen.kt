package com.example.shopnest.navigation

/**
 * @author EMRAN AHMED
 */

sealed class Screen(val route: String){
    object Auth: Screen("auth")
    object Login: Screen("login")
    object SignUp: Screen("signup")
    object Home: Screen("home")
}