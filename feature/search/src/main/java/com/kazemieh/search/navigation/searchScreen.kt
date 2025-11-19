package com.kazemieh.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kazemieh.domain.fake_data.toJson
import com.kazemieh.search.SearchScreen
import com.kazemieh.domain.model.category.CategoryOfAds
import com.kazemieh.ui.model.FromScreen

const val searchRoute = "search_route/{fromScreen}"
fun NavGraphBuilder.searchScreen(
    onSelected: (FromScreen) -> Unit,
    onBack: () -> Unit
) {
    composable(
        route = searchRoute,
        arguments = listOf(navArgument("fromScreen") { type = NavType.StringType })
    ) {

        SearchScreen(onSelected = onSelected, onBack = onBack)
    }
}

fun NavController.navigateToSearch(fromScreen: FromScreen) {
    navigate(searchRoute.replace("{fromScreen}", fromScreen.toJson()!!))
}
