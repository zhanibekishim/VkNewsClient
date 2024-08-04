package com.example.vknewsclient.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vknewsclient.R

sealed class NavigationItem(val title: Int,val icon: ImageVector) {
    data object Favorite:NavigationItem(title = R.string.favorite_title, icon = Icons.Outlined.Favorite)
    data object Profile:NavigationItem(title = R.string.profile_title, icon = Icons.Outlined.Person)
}

