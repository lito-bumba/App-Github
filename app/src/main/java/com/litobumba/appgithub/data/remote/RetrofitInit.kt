package com.litobumba.appgithub.data.remote

import com.litobumba.appgithub.common.Constants.BASE_URL
import com.litobumba.appgithub.data.remote.github_api.GithubApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInit {

    private val githubApi: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val githubService = githubApi.create(GithubApi::class.java)
}