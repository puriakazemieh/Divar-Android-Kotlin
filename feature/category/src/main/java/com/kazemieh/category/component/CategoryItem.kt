package com.kazemieh.category.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kazemieh.domain.fake_data.FakeData
import com.kazemieh.ui.utils.svgCoil
import com.kazemieh.domain.model.Category
import com.kazemieh.ui.core.text.BodyMediumText
import com.kazemieh.ui.extension.animateClickable
import com.kazemieh.ui.theme.AppTheme
import kotlin.collections.first

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.animateClickable { onClick() },
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
    ) {

        if (category.children.isNotEmpty()) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = com.kazemieh.ui.R.drawable.ic_back),
                contentDescription = "back icon",
                tint = AppTheme.colors.iconColor
            )
        }

        BodyMediumText(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            text = category.name
        )

        Spacer(modifier = Modifier.width(0.dp))

        category.icon.takeIf { it.isNotBlank() }?.let { icon ->
            AsyncImage(
                modifier = Modifier.size(20.dp),
                model = svgCoil(icon),
                contentDescription = "category icon",
                colorFilter = ColorFilter.tint(color = AppTheme.colors.iconColor),
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        CategoryItem(
            modifier = Modifier.fillMaxWidth(),
            category = FakeData.provideCategories().first(),
            onClick = {}
        )
    }
}