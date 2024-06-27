package com.uruguayemergencia.ui.navigation

import UsersViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uruguayemergencia.UruguayEmergencia
import com.uruguayemergencia.network.NetworkModule
import com.uruguayemergencia.network.UnauthorizedAccessHandler
import com.uruguayemergencia.ui.screens.HomeScreen
import com.uruguayemergencia.ui.screens.LogInScreen
import com.uruguayemergencia.ui.screens.OnboardingScreen
import com.uruguayemergencia.ui.screens.OtherScreen
import com.uruguayemergencia.ui.screens.SignUpScreen
import com.uruguayemergencia.util.functions.UtilFunctions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val dataStore = UruguayEmergencia.preferenceDataStoreHelper
    val hasToken by rememberUpdatedState(false)

    NetworkModule.initialize(LocalContext.current, object : UnauthorizedAccessHandler {
        override fun handleUnauthorizedAccess() {
            MainScope().launch {
                navController.navigate(AppScreens.OnboardingScreen.route)
            }
        }
    })

    LaunchedEffect(hasToken) {
        val token = withContext(Dispatchers.IO) {
            UtilFunctions.hasToken(dataStore)
        }
        // change else statement to 'navController.navigate(if (token) AppScreens.HomeScreen.route else AppScreens.OnboardingScreen.route)'
        navController.navigate(if (token) AppScreens.HomeScreen.route else AppScreens.HomeScreen.route)
    }

    NavHost(
        navController = navController,
        startDestination = AppScreens.LoadingScreen.route,
    ) {
        composable(route = AppScreens.LoadingScreen.route) {
        }
        composable(route = AppScreens.OnboardingScreen.route) {
            OnboardingScreen(navController)
        }
        composable(route = AppScreens.LogInScreen.route) {
            LogInScreen(navController)
        }
        composable(route = AppScreens.SignUpScreen.route) {
            SignUpScreen(navController)
        }
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = AppScreens.OtherScreen.route) {
            val usersViewModel: UsersViewModel = viewModel()
            OtherScreen(navController, usersViewModel)
        }
    }
}