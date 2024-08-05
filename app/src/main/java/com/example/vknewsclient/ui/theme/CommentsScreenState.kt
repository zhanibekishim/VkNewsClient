package com.example.vknewsclient.ui.theme

import com.example.vknewsclient.domain.CommentPost
import com.example.vknewsclient.domain.FeedPost

sealed class CommentsScreenState {

    data object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<CommentPost>
    ) : CommentsScreenState()
}