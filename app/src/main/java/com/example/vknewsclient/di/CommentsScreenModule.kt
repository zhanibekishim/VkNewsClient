package com.example.vknewsclient.di

import androidx.lifecycle.ViewModel
import com.example.vknewsclient.presentation.comments.CommentsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CommentsScreenModule{

    @Binds
    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    fun bindsCommentsViewModel(impl: CommentsViewModel): ViewModel
}