package com.kazemieh.create_ads.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kazemieh.create_ads.OnAction
import com.kazemieh.ui.R
import com.kazemieh.ui.core.text.BodyMediumText
import com.kazemieh.ui.extension.animateClickable
import com.kazemieh.ui.theme.AppTheme

@Composable
fun CreateAdsToolbar(
    onAction: OnAction,
    onClose: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.itemColor)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.Refresh,
            contentDescription = "",
            tint = AppTheme.colors.iconColor
        )

        BodyMediumText(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            textAlign = TextAlign.Start,
            text = stringResource(id = R.string.insert_ads)
        )

        Icon(
            modifier = Modifier
                .size(24.dp)
                .animateClickable { onClose() },
            imageVector = Icons.Default.Close,
            contentDescription = "",
            tint = AppTheme.colors.iconColor
        )
    }
}
