package com.example.healthapp.ui.daily

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.healthapp.data.database.entities.UserDailyData
import com.example.healthapp.ui.theme.HealthBlue

@Composable
fun DailyEntryDialog(
    entry: UserDailyData,
    setShowDialog: (Boolean) -> Unit
) {
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Card(
            shape = MaterialTheme.shapes.small.copy(
                CornerSize(20.dp)
            )
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Water: ${entry.water}l"
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Blood pressure: ${entry.bloodPressure}"
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Steps: ${entry.steps}"
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Sleep: ${entry.sleep}h"
                )
                if (!entry.workouts.isNullOrEmpty())
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Workouts: ${entry.workouts}".replace("[", "").dropLast(1)
                    )
                TextButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = { setShowDialog(false) })
                {
                    Text(
                        text = "OK",
                        color = HealthBlue
                    )
                }
            }
        }
    }
}