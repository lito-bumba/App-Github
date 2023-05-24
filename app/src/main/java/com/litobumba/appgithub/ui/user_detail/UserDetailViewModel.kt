package com.litobumba.appgithub.ui.user_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.litobumba.appgithub.data.remote.RetrofitInit
import com.litobumba.appgithub.data.repository.GithubRepositoryImpl
import com.litobumba.appgithub.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class UserDetailViewModel(private val repository: UserRepository, usrName: String) : ViewModel() {

    var state = mutableStateOf(UserDetailState())

    init {
        state.value = UserDetailState(isLoading = true)
        getUserDetailWithRepos(usrName)
    }

    fun getUserDetailWithRepos(userName: String) {
        viewModelScope.launch {
            try {
                state.value = UserDetailState(
                    user = repository.getUserDetail(userName),
                    repos = repository.getUserRepos(userName)
                )
            } catch (e: IOException) {
                state.value =
                    UserDetailState(error = "Erro na Conexão, Verifica a Conexão com a Internet")
            } catch (e: HttpException) {
                state.value = UserDetailState(error = e.localizedMessage ?: "Erro na API")
            } catch (e: Exception) {
                state.value = UserDetailState(error = e.localizedMessage ?: "Erro Desconhecido")
            }
        }
    }

    companion object {
        private val api = RetrofitInit().githubService
        fun provideFactory(
            repository: UserRepository = GithubRepositoryImpl(api),
            usrName: String
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return UserDetailViewModel(repository, usrName) as T
                }
            }
        }
    }
}