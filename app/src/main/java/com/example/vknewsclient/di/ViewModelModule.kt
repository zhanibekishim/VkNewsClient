package com.example.vknewsclient.di

import androidx.lifecycle.ViewModel
import com.example.vknewsclient.presentation.comments.CommentsViewModel
import com.example.vknewsclient.presentation.main.MainViewModel
import com.example.vknewsclient.presentation.news.NewsFeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindsMainViewModel(impl: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsFeedViewModel::class)
    fun bindsNewsFeedViewModel(impl: NewsFeedViewModel): ViewModel
}