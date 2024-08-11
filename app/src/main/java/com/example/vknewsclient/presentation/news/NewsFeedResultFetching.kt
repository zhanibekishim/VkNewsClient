package com.example.vknewsclient.presentation.news

import com.example.vknewsclient.domain.FeedPost

sealed class NewsFeedResultFetching {
    data object Initial : NewsFeedResultFetching()
    data object Loading : NewsFeedResultFetching()
    data class Success(val posts: List<FeedPost>) : NewsFeedResultFetching()
    data class Error(val message: String) : NewsFeedResultFetching()
}
