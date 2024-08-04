package com.example.vknewsclient.ui.theme


import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel){
    val scope  = rememberCoroutineScope()
    val snackBarState = SnackbarHostState()
    val fabIsVisible = remember { mutableStateOf(true) }
    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackBarState)
    }, floatingActionButton = {
        if(fabIsVisible.value){
            FloatingActionButton(onClick = {
                scope.launch {
                    val result = snackBarState.showSnackbar(
                        message = "This is snacknbar",
                        actionLabel = "Hide FAB",
                        duration = SnackbarDuration.Long)
                    if(result == SnackbarResult.ActionPerformed) {
                        fabIsVisible.value = false
                    }
                }
            }) {
                Icon(Icons.Outlined.Favorite, contentDescription = null)
            }
        }
    },bottomBar = {
        NavigationBar {
            val selectedItemPosition = remember{ mutableIntStateOf(0) }
            val items = listOf(
                NavigationItem.Favorite,
                NavigationItem.Profile)
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(text = stringResource(id = item.title)) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor =  MaterialTheme.colorScheme.onSecondary,
                        unselectedTextColor =  MaterialTheme.colorScheme.onSecondary,
                        indicatorColor =  Color.Cyan)
                )
            }
        }
    }) {contentPadding ->
            }

        }
    }
}





























