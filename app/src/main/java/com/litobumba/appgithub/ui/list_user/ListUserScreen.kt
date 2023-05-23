package com.litobumba.appgithub.ui.list_user

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.litobumba.appgithub.model.User
import kotlinx.coroutines.launch

@Composable
private fun ListUserContent(
    onClickSearch: () -> Unit = {},
    onClickToNavigate: (User) -> Unit = {}
) {
    val user = User(
        2334,
        name = "Lito Bumba",
        login = "lito-bumba",
        image = "https://avatars.githubusercontent.com/u/90806272?v=4"
    )
    val users = listOf(user, user, user, user, user, user, user)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "usuários do github", color = Color.Black)
                },
                actions = {
                    IconButton(onClick = { onClickSearch() }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(users) { user ->
                UserItem(user) {
                    onClickToNavigate(user)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun ListUserScreen(onClickToNavigate: (User) -> Unit = {}) {
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    BackHandler(modalBottomSheetState.isVisible) {
        coroutineScope.launch {
            modalBottomSheetState.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.8f)
                    .background(Color.Black)
            ) {

            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        ListUserContent(onClickSearch = {
            coroutineScope.launch {
                if (modalBottomSheetState.isVisible) {
                    modalBottomSheetState.hide()
                } else {
                    modalBottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
            }
        }) { onClickToNavigate(it) }
    }
}