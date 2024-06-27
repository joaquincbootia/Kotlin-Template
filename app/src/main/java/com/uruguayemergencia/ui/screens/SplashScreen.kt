package com.uruguayemergencia.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.uruguayemergencia.ui.navigation.AppNavigation
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    LaunchedEffect(Unit) {
        delay(1000)
    }
    AppNavigation()
}
