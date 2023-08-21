package com.example.testandroidapp.data.remoteDataSource


import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testandroidapp.domain.models.UserRepoModel
import java.io.IOException
import javax.inject.Inject

class UserRepoPagingSource @Inject constructor(
    private val apiDataSourceImpl: ApiDataSourceImpl,
    private val login: String
) : PagingSource<Int, UserRepoModel>() {

    override fun getRefreshKey(state: PagingState<Int, UserRepoModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserRepoModel> {
        return try {
            val page = params.key ?: 1
            val userList = apiDataSourceImpl.getAllRepos(page = page, login)
            LoadResult.Page(
                data = userList,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (userList.isEmpty()) null else page.plus(1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}