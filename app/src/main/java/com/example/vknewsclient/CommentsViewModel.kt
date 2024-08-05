package com.example.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclient.domain.CommentPost
import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.ui.theme.CommentsScreenState

class CommentsViewModel:ViewModel() {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadData(feedPost = FeedPost())
    }
    fun loadData(feedPost: FeedPost){
        val comments = mutableListOf<CommentPost>().apply {
            repeat(10){ add(CommentPost(id = it)) }
        }
        _screenState.value = CommentsScreenState.Comments(
            feedPost = feedPost,
            comments = comments)
    }
}