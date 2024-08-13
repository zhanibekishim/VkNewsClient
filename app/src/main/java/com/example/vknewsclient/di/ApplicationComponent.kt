package com.example.vknewsclient.di

import android.content.Context
import com.example.vknewsclient.presentation.ViewModelFactory
import com.example.vknewsclient.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun getViewModelFactory():ViewModelFactory

    fun getCommentsScreenComponentFactory(): CommentsScreenComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }
}