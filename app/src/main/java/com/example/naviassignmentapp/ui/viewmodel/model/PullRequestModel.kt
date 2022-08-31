package com.example.naviassignmentapp.ui.viewmodel.model

import com.example.naviassignmentapp.domain.model.UserModel

data class PullRequestModel(
    val title: String?,
    val createdDate: String?,
    val closedDate: String?,
    val user: UserModel
)