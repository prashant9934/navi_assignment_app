package com.example.naviassignmentapp.data.repository

import com.example.naviassignmentapp.domain.repository.IPullRequestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PullRequestRemoteStore @Inject constructor(private val service: PullRequestService) :
    IPullRequestRepository {
    override suspend fun getClosedPullRequests(request: IPullRequestRepository.Request): IPullRequestRepository.Response =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getPullRequest(request.state, request.pageNo)
                val pullRequests = response.map { it.getModel() }
                IPullRequestRepository.Response.Success(pullRequests)
            } catch (e: HttpException) {
                IPullRequestRepository.Response.Failure(e)
            } catch (e: IOException) {
                IPullRequestRepository.Response.Failure(e)
            }
        }
}