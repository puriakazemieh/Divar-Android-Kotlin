package com.kazemieh.splash

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kazemieh.ui.core.ui_message.UiMessageScreen
import com.kazemieh.ui.extension.baseModifier


@Composable
fun SplashScreen(
    vm: SplashViewModel = hiltViewModel(),
    onMoveToMain: () -> Unit,
    onMoveToLocation: () -> Unit,
) {

    val uiState = vm.uiState.collectAsState().value
    LocalContext.current.getActivity()?.let {
        val splashAi = it.installSplashScreen()
        splashAi.setKeepOnScreenCondition {
            uiState.userIsSelectedCity == null
        }
    }

    LaunchedEffect(key1 = uiState.userIsSelectedCity) {
        if (uiState.userIsSelectedCity == true) onMoveToMain()
        else if (uiState.userIsSelectedCity == false) onMoveToLocation()
    }


    SplashScreenContent(Modifier.baseModifier())

    UiMessageScreen(shared = vm.uiMessage)
}

@Composable
private fun SplashScreenContent(
    modifier: Modifier = Modifier,
) {


}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

@PreviewLightDark
@Composable
private fun Preview() {
    SplashScreenContent(
        modifier = Modifier.baseModifier(),
    )
}
