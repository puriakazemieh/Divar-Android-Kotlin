package com.kazemieh.divar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kazemieh.location.navigation.locationScreen
import com.kazemieh.location.navigation.navigateToLocation
import com.kazemieh.main.navigation.mainScreen
import com.kazemieh.main.navigation.navigateToMain
import com.kazemieh.splash.navigation.splashRoute
import com.kazemieh.splash.navigation.splashScreen
import com.kazemieh.ui.extension.runWithLifecycleAware

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = splashRoute
    )
    {
        splashScreen(
            onMoveToMain = {
                navController.runWithLifecycleAware { navController.navigateToMain() }
            },
            onMoveToLocation = {
                navController.runWithLifecycleAware { navController.navigateToLocation() }
            }
        )

        mainScreen()

        locationScreen(
            onMoveToMain = {
                navController.runWithLifecycleAware { navController.navigateToMain() }
            }
        )
    }

}