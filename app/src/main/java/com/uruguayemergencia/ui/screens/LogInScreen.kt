package com.uruguayemergencia.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.uruguayemergencia.R
import com.uruguayemergencia.ui.components.EmailTextField
import com.uruguayemergencia.ui.components.PasswordTextField
import com.uruguayemergencia.ui.components.isValidEmail
import com.uruguayemergencia.ui.components.isValidPassword
import com.uruguayemergencia.ui.navigation.AppScreens
import com.uruguayemergencia.viewModels.LogInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(navController: NavController) {

    Scaffold(
        topBar = {
        },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LogInScreenBody(navController)
            }
        },
    )
}

@Composable
fun LogInScreenBody(
    navController: NavController,
) {
    val logInViewModel = viewModel<LogInViewModel>()

    Box(modifier = Modifier.fillMaxWidth()) {
        ClickableText(
            text = AnnotatedString(stringResource(R.string.create_account)),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp),
            style = MaterialTheme.typography.headlineSmall,
            onClick = { navController.navigate(AppScreens.SignUpScreen.route) },
        )
    }

    Column(
        modifier = Modifier
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var showPassword by remember { mutableStateOf(false) }

        Text(
            text = stringResource(R.string.log_in),
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(modifier = Modifier.height(20.dp))

        EmailTextField(email = email, onEmailChange = { email = it })

        if (email.isNotEmpty()) {
            if (!isValidEmail(email)) {
                Text(
                    text = stringResource(R.string.hint_wrong_email),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))

        PasswordTextField(
            password = password,
            showPassword = showPassword,
            onPasswordChange = { password = it },
            onVisibleChange = { showPassword = it },
        )

        if (password.isNotEmpty()) {
            if (!isValidPassword(password)) {
                Text(
                    text = stringResource(R.string.hint_wrong_password),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .shadow(12.dp, shape = MaterialTheme.shapes.medium),
                onClick = {
                    logInViewModel.setEmailAndPassword(email, password)
                    navController.navigate(AppScreens.HomeScreen.route)
                },
            ) {
                Text(
                    text = stringResource(R.string.log_in),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        ClickableText(
            text = AnnotatedString(stringResource(R.string.forgot_password)),
            style = MaterialTheme.typography.labelMedium,
            onClick = { },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LogInScreenPreview() {
    val navController = rememberNavController()
    LogInScreen(navController = navController)
}