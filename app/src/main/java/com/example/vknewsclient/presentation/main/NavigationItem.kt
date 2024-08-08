package com.example.vknewsclient.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vknewsclient.R
import com.example.vknewsclient.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
) {

    data object Home : NavigationItem(
        screen = Screen.Home,
        titleResId = R.string.home_title,
        icon = Icons.Outlined.Home
    )

    data object Favourite : NavigationItem(
        screen = Screen.Favourite,
        titleResId = R.string.favorite_title,
        icon = Icons.Outlined.Favorite
    )

    data object Profile : NavigationItem(
        screen = Screen.Profile,
        titleResId = R.string.profile_title,
        icon = Icons.Outlined.Person
    )
}