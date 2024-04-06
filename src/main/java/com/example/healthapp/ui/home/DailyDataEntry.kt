package com.example.healthapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthapp.R
import com.example.healthapp.ui.theme.HealthAppTheme

@Composable
fun DailyDataEntry(
    entry: Triple<String, String, Int?>
) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                text = entry.first,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = entry.second,
                textAlign = TextAlign.Center
            )

            val icon = entry.third
            if (icon != null)
                Image(painter = painterResource(id = icon), contentDescription = null)
        }
    }
}

@Composable
@Preview
private fun DailyDataEntryPreview() {
    HealthAppTheme {
        DailyDataEntry(
            entry = Triple("Water", "5L", R.drawable.check),
        )
    }
}