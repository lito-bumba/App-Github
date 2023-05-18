package com.litobumba.appgithub.ui.list_user

import com.litobumba.appgithub.model.User

data class ListUserState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String = ""
)
