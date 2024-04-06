package com.example.healthapp.ui.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.healthapp.ui.components.HealthAppTextField

@Composable
fun UserInfoDialog(
    entry: UserInfoItemState,
    setShowDialog: (Boolean) -> Unit,
    onInfoChange: (String, String) -> Unit
) {
    var input by remember { mutableStateOf(TextFieldValue("")) }
    var isError by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit){
        focusRequester.requestFocus()
    }
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Card(
            shape = MaterialTheme.shapes.small.copy(
                CornerSize(20.dp)
            )
        ) {
            Box(
                modifier = Modifier.padding(20.dp)
            ) {
                HealthAppTextField(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .focusRequester(focusRequester),
                    value = input,
                    onValueChange = {
                        input = it
                        isError = false
                    },
                    label = entry.title,
                    keyboardType = entry.type,
                    isError = isError,
                    onKeyboardAction = {
                        if (input.text.isNotBlank()) {
                            onInfoChange(input.text, entry.title)
                            setShowDialog(false)
                        } else isError = true
                    })
            }
        }
    }

}