package com.kazemieh.location.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kazemieh.domain.fake_data.FakeData
import com.kazemieh.domain.model.location.City
import com.kazemieh.location.navigation.locationRoute
import com.kazemieh.ui.core.text.BodyMediumText
import com.kazemieh.ui.extension.animateClickable
import com.kazemieh.ui.theme.AppTheme

@Composable
fun CityItem(
    modifier: Modifier = Modifier,
    city: City,
    onClick: () -> Unit
) {
    BodyMediumText(
        modifier = Modifier.fillMaxWidth().animateClickable(onClick),
        text = city.name,
        textAlign = TextAlign.Start
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        CityItem(
            city = FakeData.provideCities().first()
        ) {
            
        }
    }
}