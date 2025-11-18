package com.kazemieh.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kazemieh.search.SearchScreen
import com.kazemieh.domain.model.category.CategoryOfAds

const val searchRoute = "search_route/{searchText}"
fun NavGraphBuilder.searchScreen(
    onSelected: (CategoryOfAds) -> Unit,
    onBack : () -> Unit
) {
    composable(
        route = searchRoute,
        arguments = listOf(navArgument("searchText") { type = NavType.StringType })
    ) {

        SearchScreen(onSelected = onSelected, onBack = onBack)
    }
}

fun NavController.navigateToSearch(searchText: String) {
    navigate(searchRoute.replace("{searchText}", searchText.ifEmpty { "nulll" }))
}
