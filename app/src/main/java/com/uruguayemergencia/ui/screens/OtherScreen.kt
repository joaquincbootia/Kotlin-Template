package com.uruguayemergencia.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.uruguayemergencia.R
import com.uruguayemergencia.ui.components.BottomNavigation
import com.uruguayemergencia.ui.components.HeaderTitle
import com.uruguayemergencia.ui.components.MainFloatingActionButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherScreen(navController: NavController) {
    Scaffold(
        topBar = { HeaderTitle(stringResource(R.string.other_screen)) },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
            ) {
                OtherBody(navController)
            }
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        },
        floatingActionButton = {
            MainFloatingActionButton(onClick = { })
        },
    )
}


@Composable
fun OtherBody(navController: NavController) {

}

@Preview(showBackground = true)
@Composable
fun OtherScreenPreview() {
    val navController = rememberNavController()
    OtherScreen(navController = navController)
}