package com.example.naviassignmentapp.domain.model

data class PullRequestModel(
    val title: String?,
    val createdDate: String?,
    val closedDate: String?,
    val user: UserModel
)