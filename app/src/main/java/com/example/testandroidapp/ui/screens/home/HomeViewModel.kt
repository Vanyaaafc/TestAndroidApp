package com.example.testandroidapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testandroidapp.data.remoteDataSource.ApiDataSourceImpl
import com.example.testandroidapp.data.remoteDataSource.UserPagingSource
import com.example.testandroidapp.domain.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiRepository: ApiDataSourceImpl
) : ViewModel() {

    val user: Flow<PagingData<UserModel>> = Pager(PagingConfig(pageSize = 10,prefetchDistance = 2)){
        UserPagingSource(apiRepository)
    }.flow.cachedIn(viewModelScope)

//    init {
//        viewModelScope.launch {
//            getAllUsers()
//        }
//    }
//
//    private suspend fun getAllUsers() {
//        viewModelScope.launch {
//            try {
//                val response = apiRepository.getAllUsers()
//                user = response
//                print(response)
//            } catch (e: Exception) {
//                print("${e.message}")
//            }
//        }
//    }
}