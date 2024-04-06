package com.example.healthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.healthapp.ui.NavGraphs
import com.example.healthapp.ui.components.bottomnav.HealthBottomAppBar
import com.example.healthapp.ui.destinations.DailyEntriesScreenDestination
import com.example.healthapp.ui.destinations.HomeScreenDestination
import com.example.healthapp.ui.destinations.UserScreenDestination
import com.example.healthapp.ui.theme.HealthAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route
            val viewModel = koinViewModel<MainViewModel>()
            val user = viewModel.user.value

            HealthAppTheme {
                Scaffold(
                    topBar = {
                        when (currentDestination) {
                            HomeScreenDestination.route,
                            DailyEntriesScreenDestination.route,
                            UserScreenDestination.route ->
                                TopAppBar(
                                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                                        containerColor = Color.White
                                    ),
                                    title = {
                                        Text(
                                            text = when (currentDestination) {
                                                HomeScreenDestination.route -> "Today's entries"
                                                DailyEntriesScreenDestination.route -> "Daily entries"
                                                else -> user?.username ?: ""
                                            }
                                        )
                                    })
                        }
                    },
                    bottomBar = {
                        when (currentDestination) {
                            HomeScreenDestination.route,
                            DailyEntriesScreenDestination.route,
                            UserScreenDestination.route ->
                                HealthBottomAppBar(navController)
                        }
                    }
                ) { paddingValues ->
                    DestinationsNavHost(
                        modifier = Modifier.padding(paddingValues),
                        navGraph = NavGraphs.root,
                        navController = navController,
                        dependenciesContainerBuilder = {
                            dependency(NavGraphs.root) { viewModel }
                        }
                    )
                }
            }
        }
    }
}
