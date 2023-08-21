package com.example.testandroidapp.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.testandroidapp.ui.screens.detail.components.RepoCardItem


@Composable
fun DetailPage(
    login: String,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onBackPressed: () -> Unit
) {
    val usersRepo = viewModel.userRepo.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.padding(horizontal = 20.dp)) {
        items(usersRepo) { repo ->
            repo?.let {
                Spacer(modifier = Modifier.padding(top = 20.dp))
                RepoCardItem(repo = repo)
                Spacer(modifier = Modifier.padding(bottom = 20.dp))
            }

        }
        usersRepo.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "Refresh Loading"
                            )

                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Pagination Loading")

                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }

                loadState.append is LoadState.Error -> {
                    //You can use modifier to show error message
                }
            }
        }
    }
}