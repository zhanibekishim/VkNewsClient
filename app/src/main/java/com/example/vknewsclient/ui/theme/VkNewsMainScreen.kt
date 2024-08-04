package com.example.vknewsclient.ui.theme


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vknewsclient.MainViewModel
import com.example.vknewsclient.navigation.AppNavGraph
import com.example.vknewsclient.navigation.rememberNavigationState

@Composable
fun MainScreen(viewModel: MainViewModel) {
   val navHostState = rememberNavigationState(navHostController = rememberNavController())
    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.padding(bottom = 32.dp),
                contentColor = Color.White,
                backgroundColor = Color.Black,
            ) {
                val navBackStackEntry by navHostState.navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRoute == item.screen.route,
                        onClick = { navHostState.navigateTo(item.screen.route) },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        selectedContentColor = Color.Red,
                        unselectedContentColor = Color.White
                    )
                }
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navHostState.navHostController,
            homeScreenContent = {
                HomeScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues
                )
            },
            favouriteScreenContent = { TextCounter(name = "Favourite") },
            profileScreenContent = { TextCounter(name = "Profile") }
        )
    }
}

@Composable
private fun TextCounter(name: String) {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }

    Text(
        modifier = Modifier.clickable { count++ }.padding(40.dp),
        text = "$name Count: $count",
        color = Color.Black
    )
}


























