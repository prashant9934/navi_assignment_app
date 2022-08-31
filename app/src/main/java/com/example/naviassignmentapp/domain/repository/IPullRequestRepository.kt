package com.example.naviassignmentapp.domain.repository

import androidx.paging.PagingData
import com.example.naviassignmentapp.domain.model.PullRequestModel
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

interface IPullRequestRepository {
    data class Request(val state: String, val pageNo: Int)

    fun getClosedPullRequests(): Flow<PagingData<PullRequestModel>>
}