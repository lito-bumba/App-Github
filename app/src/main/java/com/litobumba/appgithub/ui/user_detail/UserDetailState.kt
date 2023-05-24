package com.litobumba.appgithub.ui.user_detail

import com.litobumba.appgithub.model.Repo
import com.litobumba.appgithub.model.UserDetail

data class UserDetailState(
    val isLoading: Boolean = false,
    val user: UserDetail = UserDetail(0, "", "", "", ""),
    val repos: List<Repo> = emptyList(),
    val error: String = ""
)
