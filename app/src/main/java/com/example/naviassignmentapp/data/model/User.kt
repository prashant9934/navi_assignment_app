package com.example.naviassignmentapp.data.model

import com.example.naviassignmentapp.domain.model.UserModel
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val name: String,
    @SerializedName("avatar_url")
    val avatarUrl: String?
) {
    fun getUserModel(): UserModel = UserModel(name, avatarUrl)
}