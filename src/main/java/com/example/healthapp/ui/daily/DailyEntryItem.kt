package com.example.healthapp.ui.daily

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthapp.data.database.entities.UserDailyData
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun DailyEntryItem(
    dailyEntry: UserDailyData,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9F)
            .clickable { onItemClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(16.dp),
                text = dailyEntry.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)),
                fontSize = 22.sp
            )
        }
    }
}