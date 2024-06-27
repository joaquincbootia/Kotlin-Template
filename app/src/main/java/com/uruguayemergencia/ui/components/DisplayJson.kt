package com.uruguayemergencia.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uruguayemergencia.viewModels.NotificationsViewModel
import com.uruguayemergencia.network.models.Result

@Composable
fun DisplayApiResponse(response: Result<String>) {
    when (response) {
        is Result.Loading -> {
            SpinnerTaboo()
        }

        is Result.Success -> {
            Text(text = response.data)
        }

        is Result.Error -> {
            Text(text = response.errorMessage)
        }
    }
}

@Composable
fun RawJsonApiResponse(viewModel: NotificationsViewModel) {
    val apiResponseState = viewModel.apiResponse.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DisplayApiResponse(apiResponseState.value)
    }
}

