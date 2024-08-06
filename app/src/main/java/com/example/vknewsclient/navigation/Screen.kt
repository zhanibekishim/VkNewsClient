package com.example.vknewsclient.navigation

import android.net.Uri
import com.example.vknewsclient.domain.FeedPost
import com.google.gson.Gson


sealed class Screen(
    val route: String
) {
    data object NewsFeed : Screen(ROUTE_NEWS_FEED)
    data object Favourite : Screen(ROUTE_FAVOURITE)
    data object Profile : Screen(ROUTE_PROFILE)
    data object Home: Screen(ROUTE_HOME)

    data object Comments: Screen(ROUTE_COMMENTS) {
        private const val ROUTE_FOR_ARGS = "comments"

        fun getRouteWithArgs(feedPost: FeedPost): String {
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${feedPostJson.encode()}"
        }
    }

    companion object {
        const val KEY_FEED_POST = "feed_post_id"
        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVOURITE = "favourite"
        const val ROUTE_PROFILE = "profile"
    }
}
fun String.encode():String{
    return Uri.encode(this)
}