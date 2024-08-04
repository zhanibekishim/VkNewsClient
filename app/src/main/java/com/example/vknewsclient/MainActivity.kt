package com.example.vknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.example.vknewsclient.ui.theme.MainScreen
import com.example.vknewsclient.ui.theme.VkNewsClientTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    private val viewModel  by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkNewsClientTheme {
                Test(viewModel = viewModel)
            }
        }
    }
}
@Composable
fun Test(viewModel :MainViewModel){
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = useDarkIcons
        )
        systemUiController.isNavigationBarVisible = true
        systemUiController.isStatusBarVisible = true
    }
    MainScreen(viewModel)
}

