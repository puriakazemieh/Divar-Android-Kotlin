package com.kazemieh.divar.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kazemieh.ads.navigation.navigateToAds
import com.kazemieh.feature.navigation.filterScreen
import com.kazemieh.feature.navigation.navigateToFilter
import com.kazemieh.location.navigation.locationScreen
import com.kazemieh.location.navigation.navigateToLocation
import com.kazemieh.main.navigation.mainScreen
import com.kazemieh.main.navigation.navigateToMain
import com.kazemieh.search.navigation.navigateToSearch
import com.kazemieh.search.navigation.searchScreen
import com.kazemieh.splash.navigation.splashRoute
import com.kazemieh.splash.navigation.splashScreen
import com.kazemieh.ui.extension.runWithLifecycleAware

@Composable
fun AppNavigation() {

    val rootNavController = rememberNavController()
    val mainNavController = rememberNavController()


    NavHost(
        navController = rootNavController,
        startDestination = splashRoute
    )
    {
        splashScreen(
            onMoveToMain = {
                rootNavController.navigateToMain()
//                rootNavController.runWithLifecycleAware { rootNavController.navigateToMain() }
            },
            onMoveToLocation = {
                Log.d("949494", "AppNavigation: onMoveToLocation")
                rootNavController.navigateToLocation()
//                rootNavController.runWithLifecycleAware { rootNavController.navigateToLocation() }
            }
        )

        mainScreen(
            bottomBarItems = provideBottomBars(),
            mainNavigation = {
                MainNavigation(
                    navController = mainNavController,
                    onSearch = {
                        rootNavController.runWithLifecycleAware {
                            navigateToSearch(it)
                        }
                    },
                    onCity = {},
                    onFilter = {
                        rootNavController.navigateToFilter(it)
                    }
                )
            },
            onChangeBottomBar = {
                it.route.takeIf { bottomBarItem -> bottomBarItem.isNotEmpty() }?.let { route ->
                    mainNavController.runWithLifecycleAware {
                        navigate(route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(mainNavController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            // Avoid multiple copies of the same destination when
                            // re-selecting the same item
                            launchSingleTop = true

                            // Restore state when re-selecting a previously selected item
                            restoreState = true
                        }
                    }
                }
            }
        )



        locationScreen(
            onMoveToMain = {
                rootNavController.runWithLifecycleAware { rootNavController.navigateToMain() }
            }
        )

        searchScreen(
            onSelected = {
                rootNavController.popBackStack()
                mainNavController.navigateToAds(it)
            },
            onBack = {
                rootNavController.popBackStack()
            }
        )

        filterScreen(
            onBack = {
                rootNavController.popBackStack()
            },
            onSaveFilter = {
                rootNavController.popBackStack()
                mainNavController.popBackStack()
                mainNavController.navigateToAds(it)
            }
        )
    }

}