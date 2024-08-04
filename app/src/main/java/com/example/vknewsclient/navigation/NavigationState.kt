package com.example.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

class NavigationState(
    val navHostController: NavHostController
) {
    fun navigateTo(route: String){
        navHostController.navigate(route){
            navHostController.graph.startDestinationRoute?.let {
                popUpTo(it)
                { saveState = true }
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
@Composable
fun rememberNavigationState(
    navHostController: NavHostController
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}
