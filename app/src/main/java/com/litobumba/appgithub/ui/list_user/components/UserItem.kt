package com.litobumba.appgithub.ui.list_user

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.litobumba.appgithub.R
import com.litobumba.appgithub.model.User

@Preview
@Composable
fun UserItem(
    user: User = User(
        id = 1,
        login = "Mobile Developer",
        name = "Lito Bumba",
        image = ""
    ),
    onClickItem: (User) -> Unit = {}
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
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .height(80.dp)
                    .width(80.dp)
                    .clip(CircleShape)

            )
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = user.login,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}