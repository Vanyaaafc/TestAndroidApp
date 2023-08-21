package com.example.testandroidapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.testandroidapp.ui.screens.home.components.UserCardItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {

    val users = viewModel.user.collectAsLazyPagingItems()
    Scaffold() {
        it
        LazyColumn(
            modifier = Modifier.padding(all = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(users) { user ->
                user?.let { userModel ->
                    UserCardItem(userModel, navController = navController)
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                }
            }
            users.apply {
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
}