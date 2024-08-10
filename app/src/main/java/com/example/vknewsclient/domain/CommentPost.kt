package com.example.vknewsclient.domain

data class CommentPost(
    val id :Long,
    val commentText:String,
    val publicationDate:String,
    val authorAvatarUrl:String,
    val authorName:String
)