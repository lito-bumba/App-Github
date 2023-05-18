package com.litobumba.appgithub.repository

import com.litobumba.appgithub.common.Resource
import com.litobumba.appgithub.model.Repo
import com.litobumba.appgithub.model.User
import com.litobumba.appgithub.model.UserDetail
import retrofit2.HttpException
import java.io.IOException

class UserUseCases(
    private val repository: UserRepository
) {

    suspend fun getUsers(): Resource<List<User>> {
        return try {
            Resource.Success(repository.getUsers())
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An un")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server, Check your connection")
        }
    }

    suspend fun getUserDetail(userName: String): Resource<UserDetail> {
        return try {
            Resource.Success(repository.getUserDetail(userName))
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An un")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server, Check your connection")
        }
    }

    suspend fun getUserRepos(userName: String): Resource<List<Repo>> {
        return try {
            Resource.Success(repository.getUserRepos(userName))
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An un")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server, Check your connection")
        }
    }
}
