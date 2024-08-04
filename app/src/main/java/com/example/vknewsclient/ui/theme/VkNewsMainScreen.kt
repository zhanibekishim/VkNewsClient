package com.example.vknewsclient.ui.theme


import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vknewsclient.domain.MainViewModel
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
                NavigationItem.Main,
                NavigationItem.Favorite,
                NavigationItem.Profile)
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedItemPosition.intValue==index,
                    onClick = {selectedItemPosition.intValue = index},
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
        val feedPosts = viewModel.feedPosts.observeAsState(listOf())

        LazyColumn(
            modifier = Modifier.padding(contentPadding),
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 72.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(feedPosts.value, key = {it.id}){feedPost->
                val dismissThresholds =  with(LocalDensity.current){
                    LocalConfiguration.current.screenWidthDp.dp.toPx()*0.5F
                }
                val dismissBoxState = rememberSwipeToDismissBoxState(
                    positionalThreshold = {dismissThresholds},
                    confirmValueChange = {value ->
                        val isDismissed = value in setOf(
                            SwipeToDismissBoxValue.StartToEnd,
                            SwipeToDismissBoxValue.EndToStart
                        )
                        if(isDismissed) viewModel.remove(feedPost)
                        return@rememberSwipeToDismissBoxState isDismissed
                    }
                )
                SwipeToDismissBox(
                    modifier = Modifier.animateItemPlacement(animationSpec = tween(durationMillis = 300)),
                    enableDismissFromEndToStart = true,
                    enableDismissFromStartToEnd = false,
                    state = dismissBoxState,
                    backgroundContent = {},

                ) {
                    PostCard(
                        modifier = Modifier,
                        feedPost = feedPost,
                        onViewsClickListener = { statisticItem ->
                            viewModel.updateCount(feedPost, statisticItem)
                        },
                        onLikesClickListener = { statisticItem ->
                            viewModel.updateCount(feedPost, statisticItem)
                        },
                        onSharesClickListener = { statisticItem ->
                            viewModel.updateCount(feedPost, statisticItem)
                        },
                        onCommentClickListener = { statisticItem ->
                            viewModel.updateCount(feedPost, statisticItem)
                        }
                    )
                }

            }
        }
    }
}





























