package com.kazemieh.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.kazemieh.domain.model.category.CategoryOfAds
import com.kazemieh.ui.core.text.BodyMediumText
import com.kazemieh.ui.theme.AppTheme

@Composable
fun CategoryOfAdsItem(
    modifier: Modifier = Modifier,
    categoryOfAds: CategoryOfAds,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BodyMediumText(text = categoryOfAds.adsCount.toString())
            BodyMediumText(text = categoryOfAds.adsTitle)
        }

        BodyMediumText(
            modifier = Modifier.fillMaxWidth(),
            text = categoryOfAds.categoryName
        )
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        Box(modifier = Modifier.background(AppTheme.colors.backgroundColor))
        {
            CategoryOfAdsItem(
                categoryOfAds = CategoryOfAds(
                    categoryName = "فروش آپارتمان",
                    categoryId = 1,
                    adsCount = 2,
                    adsTitle = "آپارتمان"
                )
            ) {

            }
        }
    }
}