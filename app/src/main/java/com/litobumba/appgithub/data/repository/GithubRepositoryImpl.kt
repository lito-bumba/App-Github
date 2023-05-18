package com.litobumba.appgithub.data.repository

import com.litobumba.appgithub.data.remote.github_api.GithubApi
import com.litobumba.appgithub.data.remote.github_api.dto.toRepo
import com.litobumba.appgithub.data.remote.github_api.dto.toUser
import com.litobumba.appgithub.data.remote.github_api.dto.toUserDetail
import com.litobumba.appgithub.model.Repo
import com.litobumba.appgithub.model.User
import com.litobumba.appgithub.model.UserDetail
import com.litobumba.appgithub.repository.UserRepository

class GithubRepositoryImpl(
    private val api: GithubApi
): UserRepository {

    override suspend fun getUsers(): List<User> {
        return api.getUsers().map { it.toUser() }
    }

    override suspend fun getUserDetail(userName: String): UserDetail {
        return api.getUserDetail(userName).toUserDetail()
    }

    override suspend fun getUserRepos(userName: String): List<Repo> {
        return api.getUserRepos(userName).map { it.toRepo() }
    }
}