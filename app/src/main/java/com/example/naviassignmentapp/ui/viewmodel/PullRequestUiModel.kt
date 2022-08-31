package com.example.naviassignmentapp.ui.viewmodel

import com.example.naviassignmentapp.domain.model.PullRequestModel
import com.example.naviassignmentapp.domain.model.UserModel

class PullRequestUiModel(private val data: PullRequestModel) {
    val title: String?
        get() = data.title
    val createdDate: String?
        get() = data.createdDate
    val closedDate: String?
        get() = data.closedDate
    val user: UserModel
        get() = data.user

    override fun equals(other: Any?): Boolean {
        val pr = other as PullRequestModel
        return pr.title == title && pr.createdDate == createdDate && pr.closedDate == closedDate && pr.user.name == user.name
    }

    override fun hashCode(): Int {
        return "$title$createdDate$closedDate${user.name}".hashCode()
    }
}