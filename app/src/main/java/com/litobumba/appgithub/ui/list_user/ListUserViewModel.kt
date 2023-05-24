package com.litobumba.appgithub.ui.list_user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.litobumba.appgithub.data.remote.RetrofitInit
import com.litobumba.appgithub.data.repository.GithubRepositoryImpl
import com.litobumba.appgithub.model.User
import com.litobumba.appgithub.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ListUserViewModel(private val repository: UserRepository) : ViewModel() {

    var state = mutableStateOf(ListUserState())
        private set

    var searchState = mutableStateOf(SearchUserState())
        private set

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            try {
                state.value = ListUserState(isLoading = true)
                delay(500L)
                state.value = ListUserState(users = repository.getUsers())
            } catch (e: IOException) {
                state.value =
                    ListUserState(error = "Erro na Conexão, Verifica a Conexão com a Internet")
            } catch (e: HttpException) {
                val errorMessage = if (e.code() == 403)
                    "Atingiu o limite de requisições grátis na API do GitHub" else e.localizedMessage
                state.value = ListUserState(error = errorMessage ?: "Erro na API")
            } catch (e: Exception) {
                state.value = ListUserState(error = e.localizedMessage ?: "Erro Desconhecido")
            }
        }
    }

    fun searchUser(userName: String) {
        viewModelScope.launch {
            try {
                searchState.value = SearchUserState(
                    textSearching = userName,
                    user = repository.getUserDetail(userName)
                )
            } catch (e: IOException) {
                searchState.value = SearchUserState(
                    textSearching = userName,
                    error = "Erro na Conexão\nVerifica a Conexão com a Internet"
                )
            } catch (e: HttpException) {
                searchState.value = SearchUserState(
                    textSearching = userName,
                    error = "Usuário não encontrado\nVerifica o nome de usuário"
                )
            } catch (e: Exception) {
                searchState.value = SearchUserState(
                    textSearching = userName,
                    error = e.localizedMessage ?: "Erro Desconhecido"
                )
            }
        }
    }

    fun typingTextSearching(text: String) {
        searchState.value = searchState.value.copy(textSearching = text)
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