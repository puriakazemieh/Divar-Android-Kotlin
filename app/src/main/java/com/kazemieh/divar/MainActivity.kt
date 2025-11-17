package com.kazemieh.divar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kazemieh.divar.navigation.AppNavigation
import com.kazemieh.ui.theme.AppTheme
import com.kazemieh.ui.theme.StatusBarDark
import com.kazemieh.ui.theme.StatusBarLight
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            if (isSystemInDarkTheme()) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(StatusBarDark.toArgb()),
                    navigationBarStyle = SystemBarStyle.dark(StatusBarDark.toArgb())
                )
            } else {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(StatusBarLight.toArgb()),
                    navigationBarStyle = SystemBarStyle.dark(StatusBarLight.toArgb())
                )
            }
            AppTheme {
                AppNavigation()
            }
        }
    }
}
