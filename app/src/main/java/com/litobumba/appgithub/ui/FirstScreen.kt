package com.litobumba.appgithub.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.litobumba.appgithub.R
import kotlinx.coroutines.delay

@Preview
@Composable
fun InitialScreen(onClickStart: () -> Unit = { }) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.4f)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White,
                    modifier = Modifier.size(60.dp)
                )
                Text(
                    text = "Encontre Perfil e Repositório de Programadores ao Redor do Mundo",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 28.dp)
                        .padding(bottom = 32.dp)
                )
                AnimatedButton { onClickStart() }
            }
        }
    }
}

@Composable
private fun AnimatedButton(onClick: () -> Unit) {
    var isIconVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            isIconVisible = !isIconVisible
        }
    }

    val iconOffset by animateFloatAsState(
        targetValue = if (!isIconVisible) 0f else 40f,
        animationSpec = tween(durationMillis = 500)
    )

    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(.6f)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Iniciar",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.SemiBold,
            )
            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Back Icon",
                modifier = Modifier
                    .offset(x = iconOffset.dp)
                    .size(60.dp)
            )
        }
    }
}