package com.uruguayemergencia.ui.components

import com.uruguayemergencia.R
import com.uruguayemergencia.ui.navigation.AppScreens

enum class BottomNavigationItem(
    val navigationBarText: Int,
    val screen: AppScreens,
    val icon: Int
) {
    HOME(
        R.string.home,
        AppScreens.HomeScreen,
        R.drawable.group_deselected,
    ),
    QUIZ(
        R.string.other_screen,
        AppScreens.OtherScreen,
        R.drawable.quiz_deselected,
    ),
}
