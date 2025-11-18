package com.kazemieh.ads.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kazemieh.ads.AdsScreen
import com.kazemieh.domain.fake_data.toJson
import com.kazemieh.domain.model.filter.AdsFilter
import com.kazemieh.ui.model.FilterClickType
import java.net.URLEncoder

const val adsRoute = "ads_route/{filter}"

fun NavGraphBuilder.adsScreen(
    onCity: () -> Unit,
    onBack: () -> Unit,
    onSearch: (AdsFilter?) -> Unit,
    onFilter: (AdsFilter, FilterClickType) -> Unit
) {
    composable(
        route = adsRoute,
        arguments = listOf(navArgument("filter") { type = NavType.StringType })
    ) {
        AdsScreen(
            onBack = onBack,
            onSearch = onSearch,
            onCity = onCity,
            onFilter = onFilter
        )
    }
}

fun NavController.navigateToAds(adsFilter: AdsFilter) {
    val encoder = URLEncoder.encode(adsFilter.toJson()!! , "UTF-8")
    navigate(adsRoute.replace("{filter}", encoder))
}
