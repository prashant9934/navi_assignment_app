package com.example.naviassignmentapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.naviassignmentapp.domain.repository.IPullRequestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ClosedPullRequestListViewModel @Inject constructor(
    repository: IPullRequestRepository,
) : ViewModel() {

    val pullRequests: Flow<PagingData<PullRequestUiModel>>
        get() = _pullRequests

    private val _pullRequests =
        repository.getClosedPullRequests()
            .map { pagingData ->
                pagingData.map { pullRequest ->
                    PullRequestUiModel(pullRequest)
                }
            }.cachedIn(viewModelScope)

}