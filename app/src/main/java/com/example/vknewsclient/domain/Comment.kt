package com.example.vknewsclient.domain

import com.example.vknewsclient.R

data class Comment(
    val id :Int,
    val contentText:String = "Long comment text",
    val publicationDate:String = "14:00",
    val authorAvatarId:Int = R.drawable._eujynyflbqz0nnjprzg3zrqgdm,
    val authorName:String = "Zhanibek"
)