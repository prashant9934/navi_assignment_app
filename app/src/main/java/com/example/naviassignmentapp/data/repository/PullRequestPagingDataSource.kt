package com.example.naviassignmentapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.naviassignmentapp.domain.model.PullRequestModel
import com.example.naviassignmentapp.domain.model.PullRequestState
import javax.inject.Inject

const val PAGE_SIZE = 30
private const val STARTING_PAGE_INDEX = 1

class PullRequestPagingDataSource @Inject constructor(
    private val service: PullRequestService
) : PagingSource<Int, PullRequestModel>() {
    override fun getRefreshKey(state: PagingState<Int, PullRequestModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PullRequestModel> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = service.getPullRequest(PullRequestState.CLOSED.key, pageIndex)
            val pullRequests = response.map { it.getModel() }
            val nextKey = if (pullRequests.isEmpty() || pullRequests.size != PAGE_SIZE) {
                null
            } else {
                pageIndex.plus(1)
            }
            LoadResult.Page(
                data = pullRequests,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}