package com.example.testandroidapp.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testandroidapp.data.remoteDataSource.ApiDataSourceImpl
import com.example.testandroidapp.data.remoteDataSource.UserPagingSource
import com.example.testandroidapp.data.remoteDataSource.UserRepoPagingSource
import com.example.testandroidapp.domain.models.UserModel
import com.example.testandroidapp.domain.models.UserRepoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val apiDataSourceImpl: ApiDataSourceImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val login: String = savedStateHandle.get<String>("login")!!

    val userRepo: Flow<PagingData<UserRepoModel>> =
        Pager(PagingConfig(pageSize = 10, prefetchDistance = 2)) {
            UserRepoPagingSource(apiDataSourceImpl, login)
        }.flow.cachedIn(viewModelScope)
}