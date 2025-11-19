package com.kazemieh.divar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kazemieh.ads.navigation.adsScreen
import com.kazemieh.ads.navigation.navigateToAds
import com.kazemieh.category.navigation.categoryScreen
import com.kazemieh.chat.navigation.chatScreen
import com.kazemieh.home.navigation.homeRoute
import com.kazemieh.home.navigation.homeScreen
import com.kazemieh.main.navigation.mainRoute
import com.kazemieh.profile.navigation.profileScreen
import com.kazemieh.ui.extension.runWithLifecycleAware
import com.kazemieh.ui.model.FromScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    onSearch: (FromScreen) -> Unit,
    onFilter: (FromScreen) -> Unit,
    onCity: () -> Unit
) {

    NavHost(
        navController = navController,
        route = mainRoute,
        startDestination = homeRoute
    )
    {
        homeScreen(
            onCity = {},
            onSearch = { onSearch(FromScreen.Home) },
            onSelectedCategory = {
                navController.runWithLifecycleAware {
                    navigateToAds(FromScreen.Home)
                }
            }
        )

        categoryScreen(
            onCategory = {
                navController.runWithLifecycleAware {
                    navigateToAds(FromScreen.Category)
                }
            }
        )

        chatScreen()

        profileScreen()

        adsScreen(
            onBack = { navController.popBackStack() },
            onSearch = onSearch,
            onCity = onCity,
            onFilter = onFilter
        )
    }
}