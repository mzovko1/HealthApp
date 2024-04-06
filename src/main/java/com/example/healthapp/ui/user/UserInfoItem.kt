package com.example.healthapp.ui.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthapp.R
import com.example.healthapp.ui.theme.HealthAppTheme

@Composable
fun UserInfoItem(
    title: String,
    content: String,
    onEditClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(text = content)
        Spacer(modifier = Modifier.weight(1F))
        IconButton(onClick = { onEditClicked() }) {
            Image(painter = painterResource(id = R.drawable.edit), contentDescription = null)
        }
    }
}

@Composable
@Preview
private fun UserInfoItemPreview() {
    HealthAppTheme {
        UserInfoItem(
            title = "Height",
            content = "170cm",
            onEditClicked = { }
        )
    }
}