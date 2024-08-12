package com.example.vknewsclient.presentation.comments

import com.example.vknewsclient.domain.entity.CommentPost
import com.example.vknewsclient.domain.entity.FeedPost

sealed class CommentsScreenState {

    data object Initial : CommentsScreenState()
    data object IsDataLoading : CommentsScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<CommentPost>
    ) : CommentsScreenState()
}