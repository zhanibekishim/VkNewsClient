package com.example.vknewsclient.presentation.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vknewsclient.domain.entity.FeedPost

class CommentsViewModelFactory(private val application: Application,private val feedPost: FeedPost):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost = feedPost, application = application) as T
    }
}