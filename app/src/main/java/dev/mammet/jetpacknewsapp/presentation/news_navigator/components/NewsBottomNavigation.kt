package dev.mammet.jetpacknewsapp.presentation.news_navigator.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mammet.jetpacknewsapp.R
import dev.mammet.jetpacknewsapp.presentation.Dimens
import dev.mammet.jetpacknewsapp.ui.theme.JetpackNewsAppTheme

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {

        items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(bottomNavigationItem.icon),
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding2))

                        Text(
                            text = bottomNavigationItem.text,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(R.color.body),
                    unselectedTextColor = colorResource(R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                ),
            )
        }

    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String,
)

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NewsBottomNavigationPrev() {
    JetpackNewsAppTheme {
        NewsBottomNavigation(
            items = listOf(
                BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
                BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
                BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
            ),
            selected = 0,
            onItemClick = {}
        )
    }

}