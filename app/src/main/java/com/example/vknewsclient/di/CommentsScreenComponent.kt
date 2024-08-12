package com.example.vknewsclient.di

import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [CommentsScreenModule::class]
)
interface CommentsScreenComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory{
        fun create(
            @BindsInstance feedPost: FeedPost
        ):CommentsScreenComponent
    }
}