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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.uruguayemergencia.viewModels.SignUpViewModel
import com.uruguayemergencia.R
import com.uruguayemergencia.ui.components.EmailTextField
import com.uruguayemergencia.ui.components.PasswordTextField
import com.uruguayemergencia.ui.components.isValidEmail
import com.uruguayemergencia.ui.components.isValidPassword
import com.uruguayemergencia.ui.navigation.AppScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var isLoading by remember { mutableStateOf(false) }

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
                SignUpBody(navController, snackbarHostState, coroutineScope) {
                    isLoading = it
                }
            }

            if (isLoading) {
                LoadingScreen()
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    )
}

@Composable
fun SignUpBody(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    setLoading: (Boolean) -> Unit
) {
    val signUpViewModel = viewModel<SignUpViewModel>()
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
        ClickableText(
            text = AnnotatedString(stringResource(R.string.log_in)),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp),
            style = MaterialTheme.typography.headlineSmall,
            onClick = { navController.navigate(AppScreens.LogInScreen.route) },
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
            text = stringResource(R.string.create_account),
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
                colors = ButtonDefaults.outlinedButtonColors(MaterialTheme.colorScheme.tertiary),
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .shadow(12.dp, shape = MaterialTheme.shapes.medium),
                onClick = {
                    setLoading(true)
                    signUpViewModel.setEmailAndPassword(email, password)
                    signUpViewModel.signUpUser()
                },
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}