package com.example.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclient.domain.CommentPost
import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.ui.theme.CommentsScreenState

class CommentsViewModel(feedPost: FeedPost)
    :ViewModel() {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(feedPost = feedPost)
    }
    private fun loadComments(feedPost: FeedPost){
        val comments = mutableListOf<CommentPost>().apply {
            repeat(10){ add(CommentPost(id = it)) }
        }
        _screenState.value = CommentsScreenState.Comments(
            feedPost = feedPost,
            comments = comments)
    }
}