package com.example.healthapp.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthapp.ui.theme.HealthAppTheme
import com.example.healthapp.ui.theme.HealthBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthAppTextField(
    modifier: Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    placeholder: String = "",
    isError: Boolean = false,
    supportingText: String = "",
    keyboardType: KeyboardType,
    onKeyboardAction: () -> Unit,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = {
            Text(
                text = placeholder,
                color = Color.Gray
            )
        },
        singleLine = singleLine,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = supportingText)
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(
            onNext = { onKeyboardAction() }
        ),
        visualTransformation = visualTransformation,
        shape = TextFieldDefaults.outlinedShape,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = HealthBlue,
            focusedLabelColor = HealthBlue,
            textColor = Color.Black,
            errorCursorColor = Color.Red,
            errorSupportingTextColor = Color.Red,
            errorBorderColor = Color.Red,
            errorLabelColor = Color.Red
        )
    )
}

@Composable
@Preview
private fun HealthAppTextFieldPreview() {
    HealthAppTheme {
        HealthAppTextField(
            modifier = Modifier,
            value = TextFieldValue("test"),
            onValueChange = { },
            isError = false,
            label = "Test",
            placeholder = "....",
            supportingText = "",
            keyboardType = KeyboardType.Text,
            onKeyboardAction = { }
        )
    }
}