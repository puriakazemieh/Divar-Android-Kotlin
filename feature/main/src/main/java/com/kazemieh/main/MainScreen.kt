package com.kazemieh.main

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.kazemieh.ui.core.text.BodyMediumText
import com.kazemieh.ui.core.ui_message.UiMessageScreen
import com.kazemieh.ui.extension.baseModifier

@Composable
fun MainScreen(
    vm: MainViewModel = hiltViewModel(),
) {
    val uiState = vm.uiState.collectAsState().value

    MainScreenContent(Modifier.baseModifier())

    UiMessageScreen(shared = vm.uiMessage)
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        BodyMediumText(text = "Main Screen")
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    MainScreenContent(
        modifier = Modifier.baseModifier(),
    )
}
