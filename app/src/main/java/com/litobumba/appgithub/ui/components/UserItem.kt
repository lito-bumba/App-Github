package com.litobumba.appgithub.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.litobumba.appgithub.model.User

@Composable
fun UserItem(
    user: User,
    onClickItem: (User) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onClickItem(user)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = user.image,
                contentDescription = "Profile Image",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .height(80.dp)
                    .width(80.dp)
                    .clip(CircleShape)
            )
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = user.userName,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = user.id.toString(),
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}