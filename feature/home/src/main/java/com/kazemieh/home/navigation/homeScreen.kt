package com.kazemieh.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kazemieh.domain.model.category.Category
import com.kazemieh.home.HomeScreen

const val homeRoute = "home_route"
fun NavGraphBuilder.homeScreen(
    onCity: () -> Unit,
    onSearch: () -> Unit,
    onSelectedCategory: (Category) -> Unit,
    onAdsClick: (Long) -> Unit
) {
    composable(
        route = homeRoute,
    ) {
        HomeScreen(
            onCity = onCity,
            onSearch = onSearch,
            onSelectedCategory = onSelectedCategory,
            onAdsClick = onAdsClick
        )
    }
}

fun NavController.navigateToHome() {
    navigate(homeRoute)
}
