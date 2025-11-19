package com.kazemieh.main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kazemieh.main.component.BottomBarItemScreen
import com.kazemieh.main.fake_data.MainFakeData
import com.kazemieh.main.model.BottomBarItem
import com.kazemieh.ui.core.ui_message.UiMessageScreen
import com.kazemieh.ui.extension.baseModifier
import kotlinx.collections.immutable.ImmutableList

@Composable
fun MainScreen(
    vm: MainViewModel = hiltViewModel(),
    bottomBarItems: ImmutableList<BottomBarItem>,
    mainNavigation: @Composable () -> Unit,
    onChangeBottomBar: (BottomBarItem, Boolean) -> Unit
) {
    val uiState = vm.uiState.collectAsState().value
    Log.d("949494", "MainScreen: ")
    LaunchedEffect(key1 = uiState.selectedIndex) {
        onChangeBottomBar(bottomBarItems[uiState.selectedIndex], uiState.isUserLoggedIn)
        if (uiState.selectedIndex == 2) {
            vm.onTriggerEvent(MainUiEvent.OnChangeTab(4))
        }
    }

    MainScreenContent(
        Modifier.baseModifier(0.dp),
        bottomBarItems = bottomBarItems,
        onAction = { vm.onTriggerEvent(it) },
        selectedIndex = uiState.selectedIndex,
        mainNavigation = mainNavigation
    )

    UiMessageScreen(shared = vm.uiMessage)
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    bottomBarItems: ImmutableList<BottomBarItem>,
    mainNavigation: @Composable () -> Unit,
    onAction: OnAction,
    selectedIndex: Int = 4
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomBarItemScreen(
                bottomBarItems = bottomBarItems,
                selectedIndex = selectedIndex,
                onAction = onAction,
            )
        },
        content = {
            Box(modifier = Modifier.padding(it))
            {
                mainNavigation()
            }
        }
    )
}


@PreviewLightDark
@Composable
private fun Preview() {
    MainScreenContent(
        modifier = Modifier.baseModifier(),
        bottomBarItems = MainFakeData.provideBottomBars(),
        onAction = {},
        mainNavigation = {}
    )
}
