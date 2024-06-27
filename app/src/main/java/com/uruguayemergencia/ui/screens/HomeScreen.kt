package com.uruguayemergencia.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.uruguayemergencia.R
import com.uruguayemergencia.ui.components.BottomNavigation
import com.uruguayemergencia.ui.components.HeaderTitle
import com.uruguayemergencia.ui.components.ListItem
import com.uruguayemergencia.ui.components.MainFloatingActionButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { HeaderTitle(stringResource(R.string.home)) },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
            ) {
                HomeBody(navController)
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
fun HomeBody(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        items(20) { index ->
            ListItem(
                text = "Item: $index",
                rowHeight = 75,
                hasDivider = true,
                onItemClick = { },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}