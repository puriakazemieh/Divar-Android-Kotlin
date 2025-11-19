package com.kazemieh.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kazemieh.domain.fake_data.toJson
import com.kazemieh.feature.FilterScreen
import com.kazemieh.ui.model.FromScreen

const val filterRoute = "filter_route/{fromScreen}"
fun NavGraphBuilder.filterScreen(
    onBack: () -> Unit,
    onSaveFilter: (FromScreen) -> Unit,
) {
    composable(
        route = filterRoute,
        arguments = listOf(navArgument("fromScreen") { type = NavType.StringType })
    ) {
        FilterScreen(onBack = onBack, onSaveFilter = onSaveFilter)
    }
}

fun NavController.navigateToFilter(
    fromScreen: FromScreen
) {
    navigate(
        filterRoute.replace("{fromScreen}", fromScreen.toJson()!!)
    )
}
