package com.uruguayemergencia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(navController: NavController) {
    val items = BottomNavigationItem.values()
    NavigationBar(
        modifier = Modifier
            .height(height = 78.dp)
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            val navigationItemTitle = stringResource(id = item.navigationBarText)
            NavigationBarItem(
                icon = {
                    CustomIcon(
                        drawableId = item.icon,
                        contentDescription = navigationItemTitle,
                    )
                },
                label = {
                    Text(
                        text = navigationItemTitle,
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        // Add navigation options if needed
                    }
                },
            )
        }
    }
}
