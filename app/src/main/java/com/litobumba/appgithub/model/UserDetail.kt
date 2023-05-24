package com.litobumba.appgithub.model

data class UserDetail(
    val id: Int,
    val userName: String,
    val name: String,
    val bio: String,
    val image: String
)

fun UserDetail.toUser(): User {
    return User(
        id = this.id,
        userName = this.userName,
        image = this.image
    )
}