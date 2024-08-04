package com.example.vknewsclient.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vknewsclient.R
import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.domain.StatisticItem
import com.example.vknewsclient.domain.StatisticType

@Composable
fun PostCard(
    modifier: Modifier,
    feedPost: FeedPost,
    onLikesClickListener: (StatisticItem) -> Unit = {},
    onCommentClickListener: (StatisticItem) -> Unit = {},
    onViewsClickListener: (StatisticItem) -> Unit = {},
    onSharesClickListener: (StatisticItem) -> Unit = {},
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            PostHeader(feedPost = feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = feedPost.contentText)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = feedPost.contentResId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(8.dp))
            Statistics(
                feedPost.statistics,
                onLikesClickListener = onLikesClickListener,
                onCommentClickListener = onCommentClickListener,
                onViewsClickListener = onViewsClickListener,
                onSharesClickListener = onSharesClickListener,
                )
        }
    }
}

@Composable
private fun PostHeader(feedPost: FeedPost) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = feedPost.communityName,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}
@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onLikesClickListener: (StatisticItem) -> Unit = {},
    onCommentClickListener: (StatisticItem) -> Unit = {},
    onViewsClickListener: (StatisticItem) -> Unit = {},
    onSharesClickListener: (StatisticItem) -> Unit = {},
) {
    Row {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            val viewsItem = statistics.getIconResIdByType(StatisticType.VIEWS)
            IconWithText(
                iconResId = R.drawable.ic_count_views,
                text = viewsItem.count.toString(),
                onItemClickListener = {
                    onViewsClickListener(viewsItem)
                })
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val likesItem  = statistics.getIconResIdByType(StatisticType.LIKES)
            val sharesItem  = statistics.getIconResIdByType(StatisticType.SHARES)
            val commentsItem  = statistics.getIconResIdByType(StatisticType.COMMENTS)
            IconWithText(
                iconResId = R.drawable.ic_like,
                text = likesItem.count.toString(),
                onItemClickListener = {
                    onLikesClickListener(likesItem)
                })
            IconWithText(
                iconResId = R.drawable.ic_share,
                text = sharesItem.count.toString(),
                onItemClickListener = {
                    onSharesClickListener(sharesItem)
                })
            IconWithText(
                iconResId = R.drawable.ic_comment,
                text = commentsItem.count.toString(),
                onItemClickListener = {
                    onCommentClickListener(commentsItem)
                })
        }
    }
}
private fun List<StatisticItem>.getIconResIdByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw IllegalAccessException("")
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onItemClickListener: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onItemClickListener()
        }
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}
@Composable
@Preview
fun DarkTheme(){
    VkNewsClientTheme(darkTheme = true){
        PostCard(modifier = Modifier, feedPost = FeedPost())
    }
}
@Composable
@Preview
fun LightTheme(){
    VkNewsClientTheme(darkTheme = false){
        PostCard(modifier = Modifier, feedPost = FeedPost())
    }
}