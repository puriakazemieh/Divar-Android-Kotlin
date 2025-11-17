package com.kazemieh.category.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kazemieh.category.CategoryScreen

const val categoryRoute = "category_route"
fun NavGraphBuilder.categoryScreen(
) {
    composable(
        route = categoryRoute,
    ) {
        CategoryScreen()
    }
}

fun NavController.navigateToCategory() {
    navigate(categoryRoute)
}
