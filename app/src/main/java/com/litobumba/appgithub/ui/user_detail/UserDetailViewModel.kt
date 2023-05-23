package com.litobumba.appgithub.ui.user_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.litobumba.appgithub.data.remote.RetrofitInit
import com.litobumba.appgithub.data.repository.GithubRepositoryImpl
import com.litobumba.appgithub.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class UserDetailViewModel(private val repository: UserRepository) : ViewModel() {

    private var _state = mutableStateOf(UserDetailState())
    val state: State<UserDetailState> = _state

    init {
        getUserDetail()
        getUserRepos()
    }

    private fun getUserDetail() {
        viewModelScope.launch {
            try {
                _state.value = UserDetailState(user = repository.getUserDetail("lito-bumba"))
            } catch (e: IOException) {
                _state.value =
                    UserDetailState(error = "Erro na Conexão, Verifica a Conexão com a Internet")
            } catch (e: HttpException) {
                _state.value = UserDetailState(error = e.localizedMessage ?: "Erro na API")
            } catch (e: Exception) {
                _state.value = UserDetailState(error = e.localizedMessage ?: "Erro Desconhecido")
            }
        }
    }

    private fun getUserRepos() {
        viewModelScope.launch {
            try {
                _state.value = UserDetailState(repos = repository.getUserRepos("lito-bumba"))
            } catch (e: IOException) {
                _state.value =
                    UserDetailState(error = "Erro na Conexão, Verifica a Conexão com a Internet")
            } catch (e: HttpException) {
                _state.value = UserDetailState(error = e.localizedMessage ?: "Erro na API")
            } catch (e: Exception) {
                _state.value = UserDetailState(error = e.localizedMessage ?: "Erro Desconhecido")
            }
        }
    }

    companion object {
        private val api = RetrofitInit().githubService
        fun provideFactory(repository: UserRepository = GithubRepositoryImpl(api)): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return UserDetailViewModel(repository) as T
                }
            }
        }
    }
}