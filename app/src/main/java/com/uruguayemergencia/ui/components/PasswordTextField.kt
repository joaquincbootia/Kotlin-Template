package com.uruguayemergencia.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.uruguayemergencia.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    password: String,
    showPassword: Boolean,
    onPasswordChange: (String) -> Unit,
    onVisibleChange: (Boolean) -> Unit
) {
    OutlinedTextField(
        value = password,
        onValueChange = { onPasswordChange(it) },
        label = { Text(text = stringResource(R.string.password)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { onVisibleChange(false) }) {
                    CustomIcon(drawableId = R.drawable.visibility_off, null)
                }
            } else {
                IconButton(onClick = { onVisibleChange(true) }) {
                    CustomIcon(drawableId = R.drawable.visibility_on, null)
                }
            }
        },
    )
}

fun isValidPassword(password: String): Boolean {
    val passwordRegex = "^(?=.*?[A-Z])(?=.*?[0-9])(?=\\S+\$).{6,}\$".toRegex()
    return password.matches(passwordRegex)
}
