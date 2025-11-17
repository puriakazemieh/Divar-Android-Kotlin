package com.kazemieh.divar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kazemieh.category.navigation.categoryScreen
import com.kazemieh.chat.navigation.chatScreen
import com.kazemieh.home.navigation.homeRoute
import com.kazemieh.home.navigation.homeScreen
import com.kazemieh.main.navigation.mainRoute
import com.kazemieh.profile.navigation.profileScreen

@Composable
fun MainNavigation(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        route = mainRoute,
        startDestination = homeRoute
    )
    {
        homeScreen()

        categoryScreen()

        chatScreen()

        profileScreen()
    }
}