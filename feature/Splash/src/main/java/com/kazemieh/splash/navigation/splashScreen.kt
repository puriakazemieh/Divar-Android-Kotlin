package com.kazemieh.splash.navigation

import androidx.core.splashscreen.SplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kazemieh.splash.SplashScreen

const val splashRoute = "splash_route"

fun NavGraphBuilder.splashScreen(
    onMoveToMain: () -> Unit,
    onMoveToLocation: () -> Unit,
) {
    composable(
        route = splashRoute,
    ) {
        SplashScreen(
            onMoveToMain = onMoveToMain,
            onMoveToLocation = onMoveToLocation
        )
    }
}

fun NavController.navigateToSplash() {
    navigate(splashRoute)
}
