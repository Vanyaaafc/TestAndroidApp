package com.example.testandroidapp.data.remoteDataSource

import com.example.testandroidapp.domain.models.UserModel
import com.example.testandroidapp.domain.models.UserRepoModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDataSource {
    @GET("/users")
    suspend fun getAllUsers(
        @Query("page") page: Int,
    ): List<UserModel>

    @GET("/users/{login}/repos")
    suspend fun getAllRepos(
        @Path("login") login: String,
        @Query("page") page : Int,
    ): List<UserRepoModel>
}