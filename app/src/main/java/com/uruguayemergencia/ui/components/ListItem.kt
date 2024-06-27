package com.uruguayemergencia.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uruguayemergencia.R

@Composable
fun ListItem(
    text: String,
    image: ImageBitmap? = null,
    rowHeight: Int = 75,
    hasDivider: Boolean = false,
    onItemClick: () -> Unit = {}
) {
    val dividerHeight = 0.5.dp

    Column(
        modifier = Modifier
            .height(rowHeight.dp),
    ) {
        Row(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth()
                .clickable { onItemClick() },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(((rowHeight * 3) / 4).dp)
                    .clip(shape = CircleShape)
                    .background(color = Color.Gray),
                contentAlignment = Alignment.Center,
            ) {
                if (image == null) {
                    Image(
                        painter = painterResource(id = R.drawable.invite),
                        contentDescription = "groupImage",
                        modifier = Modifier
                            .fillMaxSize(),
                    )
                } else {
                    Image(
                        bitmap = image,
                        contentDescription = "groupImage",
                        modifier = Modifier
                            .fillMaxSize(),
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))

            Row(
                modifier = Modifier
                    .size(rowHeight.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        if (hasDivider) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Box(
                    modifier = Modifier
                        .width(54.dp),
                ) {
                }
                Spacer(modifier = Modifier.width(20.dp))
                Divider(
                    color = MaterialTheme.colorScheme.tertiary,
                    thickness = dividerHeight,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizElementPreview() {
    ListItem(
        text = "Test",
        rowHeight = 75,
        hasDivider = true,
    )
}
