package com.litobumba.appgithub.model

data class Repo(
    val name: String,
    val description: String,
    val createAt: Long,
    val stars: Int,
    val forks: Int,
    val watchers: Int
)
