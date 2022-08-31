package com.example.naviassignmentapp.domain.model

import com.example.naviassignmentapp.ui.viewmodel.model.PullRequestModel

data class PullRequestModel(
    val title: String?,
    val createdDate: String?,
    val closedDate: String?,
    val user: UserModel
) {
    fun map(): PullRequestModel {
        return PullRequestModel(title, createdDate, closedDate, user)
    }
}