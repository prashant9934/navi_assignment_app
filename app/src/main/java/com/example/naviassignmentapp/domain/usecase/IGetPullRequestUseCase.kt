package com.example.naviassignmentapp.domain.usecase

import androidx.paging.PagingSource
import com.example.naviassignmentapp.domain.model.PullRequestModel
import java.lang.Exception

interface IGetPullRequestUseCase {
    fun perform(): PagingSource<Int, PullRequestModel>
}