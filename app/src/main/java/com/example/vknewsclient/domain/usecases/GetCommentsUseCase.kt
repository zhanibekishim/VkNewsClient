package com.example.vknewsclient.domain.usecases

import com.example.vknewsclient.domain.entity.CommentPost
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetCommentsUseCase@Inject constructor(
    private val repository: NewsFeedRepository
) {

    operator fun invoke(feedPost: FeedPost): StateFlow<List<CommentPost>> {
        return repository.getComments(feedPost)
    }
}