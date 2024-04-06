package com.example.healthapp.ui.daily

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.healthapp.MainViewModel
import com.example.healthapp.R
import com.example.healthapp.data.database.entities.UserDailyData
import com.example.healthapp.ui.theme.HealthAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.time.LocalDate

@Destination
@Composable
fun DailyEntriesScreen(
    mainViewModel: MainViewModel
) {
    val dailyEntriesViewModel =
        koinViewModel<DailyEntriesViewModel> { parametersOf(mainViewModel.user.value?.username) }
    val state = dailyEntriesViewModel.uiState.collectAsStateWithLifecycle()
    when (state.value) {
        is DailyEntriesUiState.NoData -> DailyEntriesScreenContentNoData()
        is DailyEntriesUiState.Data -> DailyEntriesScreenContentData((state.value as DailyEntriesUiState.Data).dailyEntries)
    }
}

@Composable
private fun DailyEntriesScreenContentNoData() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.empty),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.Gray)
        )

        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "No previous entries!",
            fontSize = 20.sp,
            color = Color.Gray
        )
    }
}

@Composable
private fun DailyEntriesScreenContentData(
    data: List<UserDailyData>
) {
    var showAlert by remember { mutableStateOf(false) }
    var clickedEntry by remember { mutableStateOf(data[0]) }
    if (showAlert)
        DailyEntryDialog(entry = clickedEntry, setShowDialog = { showAlert = it })

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            items(data) {
                DailyEntryItem(
                    dailyEntry = it,
                    onItemClick = {
                        clickedEntry = it
                        showAlert = true
                    })
            }
        })
}

@Composable
@Preview
private fun DailyEntriesScreenContentNoDataPreview() {
    HealthAppTheme {
        DailyEntriesScreenContentNoData()
    }
}

@Composable
@Preview
private fun DailyEntriesScreenContentDataPreview() {
    HealthAppTheme {
        DailyEntriesScreenContentData(
            data = (1..5).map {
                UserDailyData(
                    date = LocalDate.now(),
                    bloodPressure = "120/80",
                    water = 5,
                    sleep = 8,
                    steps = 10000,
                    workouts = listOf("Pushups, Pullups, Burpees"),
                    user = "Matea"
                )
            }
        )
    }
}