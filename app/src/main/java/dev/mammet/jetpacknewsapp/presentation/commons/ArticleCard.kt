package dev.mammet.jetpacknewsapp.presentation.commons

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.mammet.jetpacknewsapp.R
import dev.mammet.jetpacknewsapp.domain.models.Article
import dev.mammet.jetpacknewsapp.domain.models.Source
import dev.mammet.jetpacknewsapp.presentation.Dimens.ArticleCardSize
import dev.mammet.jetpacknewsapp.presentation.Dimens.ExtraSmallPadding
import dev.mammet.jetpacknewsapp.presentation.Dimens.ExtraSmallPadding2
import dev.mammet.jetpacknewsapp.presentation.Dimens.MediumPadding1
import dev.mammet.jetpacknewsapp.presentation.Dimens.SmallIconSize
import dev.mammet.jetpacknewsapp.ui.theme.JetpackNewsAppTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {
    val context =  LocalContext.current
    Row(modifier = modifier.clickable { onClick() }) {
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Column (
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding2)
                .height(ArticleCardSize)
        ){

            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(R.color.text_title),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(R.color.body),
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Icon(
                    painter = painterResource(R.drawable.ic_time), contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = colorResource(R.color.body)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(R.color.body),
                )
            }

        }
    }

}

@Preview( showBackground = true)
@Preview( uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArticleCardPrev() {
    JetpackNewsAppTheme {
        ArticleCard(
            article = Article(
                author = "",
                content ="",
                description = "",
                publishedAt = "2 hours",
                source = Source("","BBC"),
                title = "The First Bitcoin President? Tracing Trump’s Crypto Connections",
                url = "",
                urlToImage = ""
            )
        ) { }
    }

}