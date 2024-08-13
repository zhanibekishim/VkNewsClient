package com.example.vknewsclient.presentation.news

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.presentation.ViewModelFactory
import com.example.vknewsclient.presentation.getNewsFeedApplicationComponent


@Composable
fun NewsFeedScreen(
    paddingValues: PaddingValues,
    onCommentClickListener : (FeedPost) -> Unit
) {
    val component = getNewsFeedApplicationComponent()
    val viewModel : NewsFeedViewModel = viewModel(factory = component.getViewModelFactory())
    val screenState = viewModel.screenState.collectAsState(NewsFeedScreenState.Initial)

    NewsFeedScreenContent(
        screenState = screenState,
        paddingValues = paddingValues,
        onCommentClickListener = onCommentClickListener,
        viewModel = viewModel
    )
}
@Composable
fun NewsFeedScreenContent(
    screenState: State<NewsFeedScreenState>,
    paddingValues: PaddingValues,
    onCommentClickListener : (FeedPost) -> Unit,
    viewModel:NewsFeedViewModel
){
    when (val currentState = screenState.value) {
        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                viewModel = viewModel,
                paddingValues = paddingValues,
                posts = currentState.posts,
                onCommentClickListener = onCommentClickListener,
                isDataLoading = currentState.isDataLoading
            )
        }
        NewsFeedScreenState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
                    .padding(16.dp),
                contentAlignment = Alignment.Center,
            ){
                CircularProgressIndicator(color = Color.Magenta)
            }
        }
        NewsFeedScreenState.Initial -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FeedPosts(
    viewModel: NewsFeedViewModel,
    paddingValues: PaddingValues,
    posts:List<FeedPost>,
    onCommentClickListener : (FeedPost) -> Unit,
    isDataLoading:Boolean
){
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 72.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(posts, key = {it.id}){feedPost->
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
                    onLikeClickListener = { _ ->
                        viewModel.changeLikeStatus(feedPost)
                    },
                    onCommentClickListener = {
                        onCommentClickListener(feedPost)
                    }
                )
            }
        }
        item {
            if(isDataLoading){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center,
                )
                {
                    CircularProgressIndicator(color = Color.Magenta)
                }
            }else{
               SideEffect{
                   viewModel.loadNextRecommendations()
               }
            }
        }
    }
}