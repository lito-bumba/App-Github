package com.litobumba.appgithub.ui.user_detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.litobumba.appgithub.model.Repo
import com.litobumba.appgithub.model.UserDetail

@Preview
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserDetailScreen(onClickToNavigate: () -> Unit = {}) {
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    val repos = listOf(
        Repo("Repositório Teste 1", "Testando Repositório", 10),
        Repo("Repositório Teste 2", "Testando Repositório", 10),
        Repo("Repositório Teste 3", "Testando Repositório", 10),
        Repo("Repositório Teste 4", "Testando Repositório", 15),
        Repo("Repositório Teste 31", "Testando Repositório", 12),
        Repo("Repositório Teste 13", "Testando Repositório", 103),
        Repo("Repositório Teste 12", "Testando Repositório", 101),
        Repo("Repositório Teste 11", "Testando Repositório", 1),
        Repo("Repositório Teste 33", "Testando Repositório", 0),
    )
    val userDetail = UserDetail(
        userName = "lito-bumba",
        image = "https://avatars.githubusercontent.com/u/90806272?v=4",
        name = "Lito Bumba",
        bio = "Android Developer | Jetpack Compose",
        location = "Luanda"
    )

    BackdropScaffold(
        appBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = { onClickToNavigate() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back To FirstScreen",
                        tint = Color.White
                    )
                }
            }, title = {
                Text(text = "lito-bumba", color = Color.White)
            }, backgroundColor = Color.Transparent
            )
        },
        scaffoldState = scaffoldState,
        gesturesEnabled = false,
        backLayerContent = { ProfileHeader(user = userDetail) },
        backLayerBackgroundColor = Color.Black,
        peekHeight = 250.dp,
        frontLayerContent = { ReposScreen(repos = repos) },
        frontLayerBackgroundColor = Color.White,
        frontLayerShape = RoundedCornerShape(
            topStart = 30.dp, topEnd = 30.dp
        )
    )
}

@Composable
private fun ReposScreen(repos: List<Repo>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            if (repos.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Repositórios",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        text = "13", fontSize = 20.sp, fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        items(repos) { repo ->
            RepositoryItem(repo)
        }
    }
}

@Composable
fun ProfileHeader(user: UserDetail) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxHeight(.25f)
            .padding(16.dp)
    ) {
        AsyncImage(
            model = user.image,
            contentDescription = "profile-picture",
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Location",
                    tint = Color.Red.copy(alpha = .6f),
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = user.location,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    style = MaterialTheme.typography.subtitle1
                )
            }

            Text(
                text = user.bio,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}