package com.example.vknewsclient.domain

import android.os.Bundle
import android.os.Parcelable
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.navigation.NavType
import com.example.vknewsclient.R
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedPost(
    val id:Int = 0,
    val publicationDate :String = "14:00",
    val communityName :String = "/dev/null",
    val contentText :String = "ah ah deeep pls yamete kudasaiiiii ahhh ahhhhh",
    val avatarResId :Int = R.drawable._eujynyflbqz0nnjprzg3zrqgdm,
    val contentResId :Int = R.drawable.post_content_image,
    var statistics :List<StatisticItem> = listOf(
        StatisticItem(StatisticType.VIEWS, 966),
        StatisticItem(StatisticType.SHARES, 7),
        StatisticItem(StatisticType.COMMENTS, 27),
        StatisticItem(StatisticType.LIKES, 27)
    ),
):Parcelable{
    companion object {
        val NavigationType = object: NavType<FeedPost>(false) {
            override fun get(bundle: Bundle, key: String): FeedPost? {
                return bundle.getParcelable(key)
            }

            override fun parseValue(value: String): FeedPost {
                return Gson().fromJson(value,FeedPost::class.java)
            }

            override fun put(bundle: Bundle, key: String, value: FeedPost) {
                bundle.putParcelable(key,value)
            }
        }
    }
}
