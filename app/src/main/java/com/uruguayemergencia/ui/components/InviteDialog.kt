package com.uruguayemergencia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.uruguayemergencia.R

@Composable
fun InviteDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSendInvite: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }

    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp),
                    ) {
                        Text(
                            text = "Send Invite",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 16.dp),
                        )
                        TextFieldTaboo(
                            value = email,
                            onValueChange = { newEmail ->
                                email = newEmail
                            },
                            placeholder = stringResource(R.string.email),
                        )
                        val emailIsValid = isValidEmail(email)
                        if (email.isNotEmpty()) {
                            if (!emailIsValid) {
                                Text(
                                    text = stringResource(R.string.hint_wrong_email),
                                    color = MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.labelSmall,
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            TextButton(
                                onClick = {
                                    onDismiss()
                                },
                            ) {
                                Text("Cancel")
                            }
                            Button(
                                onClick = {
                                    onSendInvite(email)
                                    onDismiss()
                                    email = ""
                                },
                                enabled = emailIsValid,
                            ) {
                                Text("Send Invite")
                            }
                        }
                    }
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InviteDialogPreview() {
    var showDialog = true
    InviteDialog(
        showDialog = showDialog,
        onDismiss = {
            showDialog = false
        },
        onSendInvite = { },
    )
}
