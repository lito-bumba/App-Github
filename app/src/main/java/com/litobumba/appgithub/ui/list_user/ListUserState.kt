package com.litobumba.appgithub.ui.list_user

import com.litobumba.appgithub.model.User
import com.litobumba.appgithub.model.UserDetail

data class ListUserState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String = ""
)

data class SearchUserState(
    val textSearching: String = "",
    val isLoading: Boolean = false,
    val user: UserDetail? = null,
    val error: String = ""
)