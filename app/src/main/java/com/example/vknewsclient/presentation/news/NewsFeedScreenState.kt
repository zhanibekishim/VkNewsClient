package com.example.vknewsclient.presentation.news

import com.example.vknewsclient.domain.FeedPost

sealed class NewsFeedScreenState {
    data object Initial : NewsFeedScreenState()
    data object Loading : NewsFeedScreenState()
    data class Posts(
        val posts: List<FeedPost>,
        val isDataLoading: Boolean = false
    ) : NewsFeedScreenState()
}