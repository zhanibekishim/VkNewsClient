package com.example.vknewsclient.presentation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.vknewsclient.di.ApplicationComponent
import com.example.vknewsclient.di.ApplicationScope
import com.example.vknewsclient.di.DaggerApplicationComponent
import com.example.vknewsclient.domain.entity.FeedPost


@ApplicationScope
class NewsFeedApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(
            this
        )
    }
}

@Composable
fun getNewsFeedApplicationComponent():ApplicationComponent{
    return (LocalContext.current.applicationContext as NewsFeedApplication).component
}