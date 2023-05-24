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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.litobumba.appgithub.model.User
import com.litobumba.appgithub.model.toUser
import com.litobumba.appgithub.ui.components.ErrorScreen
import com.litobumba.appgithub.ui.components.LoadingScreen
import com.litobumba.appgithub.ui.components.UserItem
import com.litobumba.appgithub.ui.list_user.components.InputSearch
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

@Composable
private fun ListUserContent(
    onClickSearch: () -> Unit,
    viewModel: ListUserViewModel,
    onClickToNavigate: (User) -> Unit,
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Usuários do Github", color = Color.Black)
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
            items(state.users) { user ->
                UserItem(user) {
                    onClickToNavigate(user)
                }
            }
        }
    }
}

@Preview
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListUserScreen(
    viewModel: ListUserViewModel = viewModel(factory = ListUserViewModel.provideFactory()),
    onClickToNavigate: (User) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )
    val state = viewModel.state.value

    BackHandler {
        if (modalBottomSheetState.isVisible) {
            coroutineScope.launch {
                modalBottomSheetState.hide()
            }
        } else {
            exitProcess(0)
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            val focusManager = LocalFocusManager.current
            val searchUserState = viewModel.searchState.value

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.9f)
                    .background(Color.Black),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(16.dp)
                )
                InputSearch(
                    value = searchUserState.textSearching,
                    onValueChange = {
                        viewModel.typingTextSearching(it)
                    },
                    onClickClearIcon = { viewModel.typingTextSearching("") },
                    keyboardActions = {
                        viewModel.searchUser(searchUserState.textSearching)
                        focusManager.clearFocus()
                    },
                    modifier = Modifier.fillMaxWidth(.8f)
                )

                if (searchUserState.error.isNotBlank()) {
                    Text(
                        text = searchUserState.error,
                        color = Color.Red,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                if (searchUserState.isLoading) {
                    CircularProgressIndicator()
                }

                if (searchUserState.user != null) {
                    onClickToNavigate(searchUserState.user.toUser())
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.isLoading) {
            LoadingScreen()
        }

        if (state.users.isNotEmpty()) {
            ListUserContent(
                onClickSearch = {
                    coroutineScope.launch {
                        if (modalBottomSheetState.isVisible) {
                            modalBottomSheetState.hide()
                        } else {
                            modalBottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
                    }
                },
                viewModel = viewModel
            ) { onClickToNavigate(it) }
        }

        if (state.error.isNotBlank()) {
            ErrorScreen(state.error) {
                viewModel.getUsers()
            }
        }
    }
}