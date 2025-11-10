package com.kazemieh.divar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.kazemieh.category.CategoryScreen
import com.kazemieh.ui.theme.AppTheme
import com.kazemieh.ui.theme.StatusBarDark
import com.kazemieh.ui.theme.StatusBarLight
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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
                CategoryScreen()
            }
        }
    }
}
