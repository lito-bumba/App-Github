package com.litobumba.appgithub.ui.user_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.litobumba.appgithub.model.Repo

@Preview
@Composable
fun RepositoryItem(
    repo: Repo = Repo(
        name = "App Github",
        description = "This is app that I am building just to apply in a job",
        stars = 10
    )
) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.Gray,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = repo.name,
                    color = Color.Black,
                    style = MaterialTheme.typography.h6
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = repo.stars.toString(),
                        color = Color.Black,
                        style = MaterialTheme.typography.h6
                    )
                }
            }
            if (repo.description.isNotBlank()) {
                Text(
                    text = repo.description,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}