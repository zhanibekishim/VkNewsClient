package com.example.vknewsclient.domain

import com.example.vknewsclient.R

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
)
