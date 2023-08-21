package com.example.testandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testandroidapp.ui.screens.detail.DetailPage
import com.example.testandroidapp.ui.screens.detail.DetailViewModel
import com.example.testandroidapp.ui.screens.home.HomePage
import com.example.testandroidapp.ui.screens.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            val viewModel: HomeViewModel = hiltViewModel()
            HomePage(navController = navController, viewModel = viewModel,)
        }

        composable("detail/{login}") { navBackStack ->
            val id = navBackStack.arguments?.getString("login")
            val viewModel: DetailViewModel = hiltViewModel()
            if (id != null) {
                DetailPage(
                    login = id,
                    viewModel = viewModel,
                    onBackPressed = {
                        navController.navigateUp()
                    })
            }
        }
    }
}
