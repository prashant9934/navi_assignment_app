package com.example.naviassignmentapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.example.naviassignmentapp.MainCoroutineScopeRule
import com.example.naviassignmentapp.data.exceptions.NoConnectivityException
import com.example.naviassignmentapp.data.repository.PullRequestPagingDataSource
import com.example.naviassignmentapp.data.repository.PullRequestService
import com.example.naviassignmentapp.domain.model.PullRequestModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.whenever

class PullRequestPagingDataSourceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @Mock
    lateinit var service: PullRequestService

    lateinit var sut: PullRequestPagingDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sut = PullRequestPagingDataSource(service)
    }

    @Test
    fun test_paging_source_load_failure_on_http_error() = runTest {
        val error = RuntimeException("404", Throwable())
        whenever(service.getPullRequest(any(), any())).thenThrow(error)
        val expectedResult = PagingSource.LoadResult.Error<Int, PullRequestModel>(error)
        assertEquals(
            expectedResult, sut.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun test_paging_source_load_failure_on_no_internet() = runTest {
        val error = NoConnectivityException()
        given(service.getPullRequest(any(), any())).willAnswer{ throw error }
        val expectedResult = PagingSource.LoadResult.Error<Int, PullRequestModel>(error)
        assertEquals(
            expectedResult, sut.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }
}