package com.example.naviassignmentapp.domain.model

data class PullRequestModel(
    val title: String?,
    val createdDate: String?,
    val closedDate: String?,
    val user: UserModel
) {

    override fun equals(other: Any?): Boolean {
        val pr = other as PullRequestModel
        return pr.title == title && pr.createdDate == createdDate && pr.closedDate == closedDate && pr.user.name == user.name
    }

    override fun hashCode(): Int {
        return "$title$createdDate$closedDate${user.name}".hashCode()
    }
}