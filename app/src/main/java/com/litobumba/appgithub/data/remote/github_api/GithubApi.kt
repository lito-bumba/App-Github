package com.litobumba.appgithub.data.api

import com.litobumba.appgithub.data.api.dto.RepoDto
import com.litobumba.appgithub.data.api.dto.UserDto
import com.litobumba.appgithub.model.UserDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users")
    suspend fun getUsers(): List<UserDto>

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") userName: String): UserDetail

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") userName: String): List<RepoDto>
}