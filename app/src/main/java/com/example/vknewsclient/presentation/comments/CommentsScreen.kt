package com.example.vknewsclient.presentation.comments

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.vknewsclient.R
import com.example.vknewsclient.domain.entity.CommentPost
import com.example.vknewsclient.domain.entity.FeedPost

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommentsScreen(
    feedPost : FeedPost,
    onBackPressed: () -> Unit
){
    val viewModel: CommentsViewModel = viewModel(
        factory = CommentsViewModelFactory(feedPost = feedPost,
        application = LocalContext.current.applicationContext as Application
    ))

    val screenState = viewModel.screenState.collectAsState(CommentsScreenState.Initial)

    when(val currentState = screenState.value){
        is CommentsScreenState.Comments -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = stringResource(R.string.comments))
                        },
                        navigationIcon = {
                            IconButton(onClick = { onBackPressed() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            ) { paddingValues ->
                LazyColumn(
                    modifier = Modifier.padding(paddingValues),
                    contentPadding = PaddingValues(
                        top = 16.dp,
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 72.dp
                    )
                ) {
                    items(
                        items = currentState.comments,
                        key = { it.id }
                    ) { comment ->
                        CommentItem(commentPost = comment)
                    }
                }
            }
        }
        is CommentsScreenState.IsDataLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
                    .padding(16.dp),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(color = Color.Magenta)
            }
        }
        CommentsScreenState.Initial -> {}
    }
}

@Composable
private fun CommentItem(
    commentPost: CommentPost
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 4.dp
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .clip(shape = CircleShape),
            model = commentPost.authorAvatarUrl,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = commentPost.authorName,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = commentPost.commentText,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text (

                text = commentPost.publicationDate,
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 12.sp
            )
        }
    }
}