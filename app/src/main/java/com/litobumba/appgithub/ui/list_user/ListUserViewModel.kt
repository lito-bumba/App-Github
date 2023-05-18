package com.litobumba.appgithub.ui.list_user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.litobumba.appgithub.common.Resource
import com.litobumba.appgithub.data.remote.RetrofitInit
import com.litobumba.appgithub.data.repository.GithubRepositoryImpl
import com.litobumba.appgithub.repository.UserRepository
import com.litobumba.appgithub.repository.UserUseCases

class ListUserViewModel: ViewModel() {

    private val _state = mutableStateOf(ListUserState())
    val state: State<ListUserState> = _state
    var repository: UserRepository = GithubRepositoryImpl(RetrofitInit().githubService)
    lateinit var useCases: UserUseCases

    init {
        useCases = UserUseCases(repository = repository)
    }

    suspend fun getUsers(){

    }
}