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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.litobumba.appgithub.model.Repo
import com.litobumba.appgithub.model.UserDetail
import com.litobumba.appgithub.ui.components.ErrorScreen
import com.litobumba.appgithub.ui.components.LoadingScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserDetailScreen(
    usrName: String,
    viewModel: UserDetailViewModel = viewModel(factory = UserDetailViewModel.provideFactory(usrName = usrName)),
    onClickToNavigate: () -> Unit = {}
) {
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    val state = viewModel.state.value

    if (state.isLoading){
        LoadingScreen()
        return
    }

    if (state.error.isNotBlank()){
        ErrorScreen(message = state.error) {
            viewModel.getUserDetailWithRepos(usrName)
        }
        return
    }

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
                Text(text = state.user.userName, color = Color.White)
            }, backgroundColor = Color.Transparent
            )
        },
        scaffoldState = scaffoldState,
        gesturesEnabled = false,
        backLayerContent = { ProfileHeader(user = state.user) },
        backLayerBackgroundColor = Color.Black,
        peekHeight = 250.dp,
        frontLayerContent = { ReposScreen(repos = state.repos) },
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
                        text = repos.count().toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
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
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
            Text(
                text = user.bio,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }
    }
}