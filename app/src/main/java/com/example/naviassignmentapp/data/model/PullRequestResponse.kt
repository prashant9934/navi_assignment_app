package com.example.naviassignmentapp.data.model

import com.example.naviassignmentapp.domain.model.PullRequestModel
import com.google.gson.annotations.SerializedName

data class PullRequestResponse(
    @SerializedName("title")
    val title: String?,
    @SerializedName("created_at")
    val createdDate: String?,
    @SerializedName("closed_at")
    val closedDate: String?,
    @SerializedName("user")
    val user: User
) {
    fun getModel(): PullRequestModel {
        return PullRequestModel(title, createdDate, closedDate, user.getUserModel())
    }
}