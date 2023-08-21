package com.example.testandroidapp.data.remoteDataSource

import com.example.testandroidapp.domain.models.UserModel
import com.example.testandroidapp.domain.models.UserRepoModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityRetainedScoped
class ApiDataSourceImpl @Inject constructor(
    private val apiService: ApiDataSource,
    private val defaultDispatcher: CoroutineDispatcher
) {
    suspend fun getAllUsers(page: Int): List<UserModel> {
        return withContext(defaultDispatcher) {
            apiService.getAllUsers(page)
        }
    }

    suspend fun getAllRepos(page : Int ,login: String): List<UserRepoModel> {
        return withContext(defaultDispatcher) {
            apiService.getAllRepos(login, page)
        }
    }
}