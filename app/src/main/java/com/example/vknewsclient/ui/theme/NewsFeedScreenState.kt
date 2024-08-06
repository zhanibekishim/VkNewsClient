package com.example.vknewsclient.ui.theme

import com.example.vknewsclient.domain.CommentPost
import com.example.vknewsclient.domain.FeedPost

sealed class NewsFeedScreenState {
    data object Initial : NewsFeedScreenState()
    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()
}