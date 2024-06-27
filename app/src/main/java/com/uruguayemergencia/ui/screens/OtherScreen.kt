package com.uruguayemergencia.ui.screens

import UsersViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uruguayemergencia.network.models.MockUser

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.uruguayemergencia.R
import com.uruguayemergencia.ui.components.BottomNavigation
import com.uruguayemergencia.ui.components.HeaderTitle
import com.uruguayemergencia.ui.components.MainFloatingActionButton
import com.uruguayemergencia.network.models.Result
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherScreen(navController: NavController, usersViewModel: UsersViewModel = viewModel()) {
    val userResultState = usersViewModel.getUserResult.collectAsState()

    Scaffold(
        topBar = { HeaderTitle(stringResource(R.string.other_screen)) },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
            ) {
                when (val result = userResultState.value) {
                    is Result.Success -> {
                        // Display UI for successful response
                        // Example: Text(result.data.toString())
                        OtherBody(navController, result.data)
                    }
                    is Result.Error -> {
                        // Display UI for error state
                        // Example: Text(result.errorMessage)
                    }
                    Result.Loading -> {
                        // Display loading UI
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        },
        floatingActionButton = {
            MainFloatingActionButton(onClick = { })
        },
    )

    // Fetch user data when the screen composable is first launched
    // Optionally, you can call this method wherever appropriate in your app flow
    // Remember to handle re-fetching based on your app's requirements
    usersViewModel.fetchUserData()
}

@Composable
fun OtherBody(navController: NavController, userData: MockUser) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "User ID: ${userData.userId}",
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "ID: ${userData.id}",
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Title: ${userData.title}",
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Completed: ${userData.completed}",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OtherScreenPreview() {
    val navController = rememberNavController()
    OtherScreen(navController = navController)
}