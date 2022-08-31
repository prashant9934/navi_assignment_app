package com.example.naviassignmentapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.naviassignmentapp.domain.usecase.IGetPullRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClosedPullRequestListViewModel @Inject constructor(
    private val getPullRequestUseCase: IGetPullRequestUseCase,
) : ViewModel() {

    val pullRequests: LiveData<ScreenState>
        get() = _pullRequests

    private val _pullRequests = MutableLiveData<ScreenState>(ScreenState.Loading)

    fun onCreate() {
        getPullRequests()
    }

    private fun getPullRequests() {
        viewModelScope.launch {
            when (val response = getPullRequestUseCase.perform()) {
                is IGetPullRequestUseCase.Response.Success ->
                    _pullRequests.postValue(ScreenState.Success(response.pullRequests.map { it.map() }))
                is IGetPullRequestUseCase.Response.Failure ->
                    _pullRequests.postValue(ScreenState.Error(response.error.message ?: ""))
            }
        }
    }
}