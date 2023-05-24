package com.litobumba.appgithub.model

data class UserDetail(
    val userName: String,
    val name: String,
    val bio: String,
    val image: String
)

fun UserDetail.toUser(): User {
    return User(
        id = "",
        login = this.userName,
        image = this.image
    )
}