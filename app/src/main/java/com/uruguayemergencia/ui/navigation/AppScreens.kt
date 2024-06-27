package com.uruguayemergencia.ui.navigation

sealed class AppScreens(val route: String) {
    object LoadingScreen : AppScreens("loading_screen")
    object OnboardingScreen : AppScreens("onboarding_screen")
    object LogInScreen : AppScreens("login_screen")
    object SignUpScreen : AppScreens("signup_screen")
    object HomeScreen : AppScreens("home_screen")
    object OtherScreen : AppScreens("other_screen")

}