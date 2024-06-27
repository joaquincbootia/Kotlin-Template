package com.uruguayemergencia.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.uruguayemergencia.R

@Composable
fun CustomIcon(drawableId: Int, contentDescription: String?) {
    Image(
        painter = painterResource(id = drawableId),
        contentDescription = contentDescription,
    )
}

@Preview()
@Composable
fun LogInScreenPreview() {
    CustomIcon(drawableId = R.drawable.group_deselected, contentDescription = "Groups")
}
