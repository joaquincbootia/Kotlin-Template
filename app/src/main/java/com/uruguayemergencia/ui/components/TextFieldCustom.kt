package com.uruguayemergencia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    singleLine: Boolean = true,
    multiLineMinLines: Int = 3,
    multiLineMaxLines: Int = 3,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        textStyle = MaterialTheme.typography.labelLarge,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .background(
                        color = Color(235, 230, 240),
                        shape = RoundedCornerShape(size = cornerRadius),
                    )
                    .padding(all = 16.dp)
                    .fillMaxWidth(),
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = Color(
                                150,
                                150,
                                150,
                            ),
                        ),
                    )
                }
                innerTextField()
            }
        },
        modifier = modifier,
        minLines = multiLineMinLines,
        maxLines = multiLineMaxLines,
    )
}

@Preview()
@Composable
fun SingleLineTextFieldPreview() {
    var name by remember { mutableStateOf("") }
    TextFieldCustom(
        value = name,
        onValueChange = { newName ->
            name = newName
        },
        placeholder = "Group name",
        singleLine = false,
    )
}
