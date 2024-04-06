package com.example.healthapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.healthapp.MainViewModel
import com.example.healthapp.R
import com.example.healthapp.ui.destinations.InputDataScreenDestination
import com.example.healthapp.ui.theme.HealthAppTheme
import com.example.healthapp.ui.theme.HealthBlue
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.time.LocalDate

@Composable
@Destination
fun HomeScreen(
    navigator: DestinationsNavigator,
    mainViewModel: MainViewModel
) {
    HomeContent(
        mainViewModel = mainViewModel,
        onFabClick = { navigator.navigate(InputDataScreenDestination) }
    )
}

@Composable
private fun HomeContent(
    mainViewModel: MainViewModel,
    onFabClick: () -> Unit
) {
    val navController = rememberNavController()
    navController.currentDestination?.route?.let { navController.graph.setStartDestination(it) }
    val homeViewModel = koinViewModel<HomeScreenViewModel> {
        parametersOf(
            mainViewModel.user.value?.username,
            LocalDate.now()
        )
    }
    val state = homeViewModel.uiState.collectAsStateWithLifecycle()
    when (state.value) {
        is HomeScreenUiState.NoData -> HomeContentNoData(onFabClick = { onFabClick() })
        is HomeScreenUiState.Data -> HomeContentData(
            data = homeViewModel.prepUiData((state.value as HomeScreenUiState.Data).dailyData)
        )
    }
}

@Composable
private fun HomeContentNoData(
    onFabClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = onFabClick,
            containerColor = HealthBlue,
            shape = RoundedCornerShape(20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add),
                contentDescription = null,
                tint = Color.White
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(id = R.drawable.add_data),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color.Gray)
            )

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Please input your data for today!",
                fontSize = 20.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun HomeContentData(
    data: List<Triple<String, String, Int?>>
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(data) {
            if (it.second != "")
                DailyDataEntry(entry = it)
        }
    }
}

@Composable
@Preview
private fun HomeContentNoDataPreview() {
    HealthAppTheme {
        HomeContentNoData(
            onFabClick = { }
        )
    }
}

@Composable
@Preview
private fun HomeContentDataPreview() {
    HealthAppTheme {
        HomeContentData(
            data = emptyList()
        )
    }
}
