package com.example.healthapp.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthapp.ui.theme.HealthAppTheme
import com.example.healthapp.ui.theme.HealthBlue

@Composable
fun HealthAppButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.height(45.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = HealthBlue,
            contentColor = Color.White
        ),
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Composable
@Preview
private fun HealthAppButtonPreview() {
    HealthAppTheme {
        HealthAppButton(
            modifier = Modifier,
            text = "Log in",
            onClick = {}
        )
    }
}