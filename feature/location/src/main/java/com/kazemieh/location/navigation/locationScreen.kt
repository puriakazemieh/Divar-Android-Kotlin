package com.kazemieh.location.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kazemieh.location.LocationScreen

const val locationRoute = "location_route"

fun NavGraphBuilder.locationScreen(
    onMoveToMain : () -> Unit
) {
    composable(
        route = locationRoute,
    ) {
        LocationScreen(
            onMoveToMain = onMoveToMain
        )
    }
}

fun NavController.navigateToLocation() {
    navigate(locationRoute)
}
