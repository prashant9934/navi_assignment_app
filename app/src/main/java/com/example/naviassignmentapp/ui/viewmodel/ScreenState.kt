package com.example.naviassignmentapp.ui.viewmodel

import com.example.naviassignmentapp.ui.viewmodel.model.PullRequestModel

sealed class ScreenState {
    object Loading : ScreenState()
    class Success(val data: List<PullRequestModel>) : ScreenState()
    class Error(val message: String) : ScreenState()
}