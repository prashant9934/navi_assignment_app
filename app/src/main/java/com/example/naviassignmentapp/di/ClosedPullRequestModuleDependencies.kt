package com.example.naviassignmentapp.di

import com.example.naviassignmentapp.data.repository.PullRequestRemoteStore
import com.example.naviassignmentapp.data.repository.PullRequestService
import com.example.naviassignmentapp.domain.repository.IPullRequestRepository
import com.example.naviassignmentapp.domain.usecase.GetClosedPullRequestUseCaseImpl
import com.example.naviassignmentapp.domain.usecase.IGetPullRequestUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ClosedPullRequestModuleDependencies {

    @Provides
    @ViewModelScoped
    fun providePullRequestService(retrofit: Retrofit): PullRequestService =
        retrofit.create(PullRequestService::class.java)

    @Provides
    @ViewModelScoped
    fun providePullRequestRepository(service: PullRequestService): IPullRequestRepository {
        return PullRequestRemoteStore(service)
    }

    @Provides
    @ViewModelScoped
    fun provideGetPullRequestUseCase(repository: IPullRequestRepository): IGetPullRequestUseCase {
        return GetClosedPullRequestUseCaseImpl(repository)
    }
}