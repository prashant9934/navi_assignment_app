package com.example.naviassignmentapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.naviassignmentapp.data.repository.PAGE_SIZE
import com.example.naviassignmentapp.domain.model.PullRequestModel
import com.example.naviassignmentapp.domain.usecase.IGetPullRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ClosedPullRequestListViewModel @Inject constructor(
    private val getPullRequestUseCase: IGetPullRequestUseCase,
) : ViewModel() {

    val pullRequests: Flow<PagingData<PullRequestModel>>
        get() = _pullRequests

    private val _pullRequests =
        Pager(config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = { getPullRequestUseCase.perform() }
        ).flow.cachedIn(viewModelScope)
}