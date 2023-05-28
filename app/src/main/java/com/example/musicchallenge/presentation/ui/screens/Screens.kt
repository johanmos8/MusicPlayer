package com.example.musicchallenge.presentation.ui.screens

sealed class Screens(val route: String){
    object Home : Screens(route = "home")
    object Player :
        Screens(route = "player")

}
