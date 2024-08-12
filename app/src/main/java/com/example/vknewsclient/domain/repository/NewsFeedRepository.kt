package com.example.vknewsclient.domain.repository


import com.example.vknewsclient.domain.entity.CommentPost
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.entity.AuthState
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepository {

    fun getAuthStateFlow(): StateFlow<AuthState>
    fun getRecommendations(): StateFlow<List<FeedPost>>
    fun getComments(feedPost: FeedPost): StateFlow<List<CommentPost>>

    suspend fun loadNextData()
    suspend fun checkAuthState()
    suspend fun deletePost(feedPost: FeedPost)
    suspend fun changeLikeStatus(feedPost: FeedPost)
}