package com.litobumba.appgithub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen(
    message: String,
    onClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Error",
                tint = Color.Red,
                modifier = Modifier.size(120.dp)
            )
            Text(
                text = message,
                style = MaterialTheme.typography.h5,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth()
            )
            IconButton(onClick = { onClick() }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Error",
                    tint = Color.Black,
                    modifier = Modifier.size(60.dp)
                )
            }
        }
    }
}