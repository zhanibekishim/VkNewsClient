package com.example.vknewsclient.ui.theme

import com.example.vknewsclient.domain.CommentPost
import com.example.vknewsclient.domain.FeedPost

sealed class HomeScreenState {
    data object Initial : HomeScreenState()
    data class Posts(val posts: List<FeedPost>) : HomeScreenState()
    data class Comments(val feedPost: FeedPost,val comments: List<CommentPost>) : HomeScreenState()
}