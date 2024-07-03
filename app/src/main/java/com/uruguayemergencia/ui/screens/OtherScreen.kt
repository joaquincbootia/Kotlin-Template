package com.uruguayemergencia.ui.screens

import UsersViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.uruguayemergencia.ui.components.SpinnerTaboo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherScreen(navController: NavController, usersViewModel: UsersViewModel = viewModel()) {
    val userResultState = usersViewModel.getUserResult.collectAsState()

    LaunchedEffect(Unit) {
        usersViewModel.fetchUserData()
    }

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
                        OtherBody(navController, result.data)
                    }
                    is Result.Error -> {
                        Text(
                            text = "Error: ${result.errorMessage}",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Result.Loading -> {
                        LoadingScreen()
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