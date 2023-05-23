package com.litobumba.appgithub.ui.list_user

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

class ListUserViewModel(private val repository: UserRepository) : ViewModel() {

    private var _state = mutableStateOf(ListUserState())
    val state: State<ListUserState> = _state

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            try {
                _state.value = ListUserState(users = repository.getUsers())
            } catch (e: IOException) {
                _state.value =
                    ListUserState(error = "Erro na Conexão, Verifica a Conexão com a Internet")
            } catch (e: HttpException) {
                _state.value = ListUserState(error = e.localizedMessage ?: "Erro na API")
            } catch (e: Exception) {
                _state.value = ListUserState(error = e.localizedMessage ?: "Erro Desconhecido")
            }
        }
    }

    companion object {
        private val api = RetrofitInit().githubService
        fun provideFactory(repository: UserRepository = GithubRepositoryImpl(api)): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ListUserViewModel(repository) as T
                }
            }
        }
    }
}