package com.kazemieh.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kazemieh.main.MainScreen
import com.kazemieh.main.model.BottomBarItem
import kotlinx.collections.immutable.toImmutableList

const val mainRoute = "main_route"
fun NavGraphBuilder.mainScreen(
    bottomBarItems: List<BottomBarItem>,
    mainNavigation: @Composable () -> Unit,
    onChangeBottomBar: (BottomBarItem , Boolean) -> Unit


) {
    composable(
        route = mainRoute,
    ) {
        MainScreen(
            bottomBarItems = bottomBarItems.toImmutableList(),
            mainNavigation = mainNavigation,
            onChangeBottomBar = onChangeBottomBar
        )
    }
}

fun NavController.navigateToMain() {
    navigate(mainRoute)
}
