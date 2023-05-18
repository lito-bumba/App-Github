package com.litobumba.appgithub.data.remote.github_api

import com.litobumba.appgithub.common.Constants.PARAM_USERNAME
import com.litobumba.appgithub.data.remote.github_api.dto.RepoDto
import com.litobumba.appgithub.data.remote.github_api.dto.UserDetailDto
import com.litobumba.appgithub.data.remote.github_api.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users")
    suspend fun getUsers(): List<UserDto>

    @GET("users/{username}")
    suspend fun getUserDetail(@Path(PARAM_USERNAME) userName: String): UserDetailDto

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path(PARAM_USERNAME) userName: String): List<RepoDto>
}