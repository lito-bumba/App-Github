package com.litobumba.appgithub.repository

import com.litobumba.appgithub.model.Repo
import com.litobumba.appgithub.model.User
import com.litobumba.appgithub.model.UserDetail

interface UserRepository {

    suspend fun getUsers(): List<User>

    suspend fun getUserDetail(userName: String): UserDetail

    suspend fun getUserRepos(userName: String): List<Repo>
}