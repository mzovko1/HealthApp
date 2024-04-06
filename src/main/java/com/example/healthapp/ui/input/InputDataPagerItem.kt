package com.example.healthapp.ui.input

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthapp.R
import com.example.healthapp.ui.components.HealthAppButton
import com.example.healthapp.ui.components.HealthAppTextField

data class InputDataPagerItemState(
    val title: String,
    val image: Int,
    val input: TextFieldValue = TextFieldValue(""),
    val onInputChange: (TextFieldValue) -> Unit,
    val isInputError: Boolean = false,
    val keyboardType: KeyboardType,
    val onKeyboardAction: () -> Unit,
    val buttonText: String = "Next",
    val onButtonClick: () -> Unit
)

@Composable
fun InputDataPagerItem(
    state: InputDataPagerItemState
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit){
        focusRequester.requestFocus()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 24.dp),
            text = state.title,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )

        Image(
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 20.dp),
            painter = painterResource(id = state.image),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )

        HealthAppTextField(
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .padding(bottom = 10.dp)
                .focusRequester(focusRequester),
            value = state.input,
            onValueChange = state.onInputChange,
            label = state.title,
            isError = state.isInputError,
            keyboardType = state.keyboardType,
            onKeyboardAction = state.onKeyboardAction
        )

        HealthAppButton(
            modifier = Modifier.fillMaxWidth(0.9F),
            text = state.buttonText,
            onClick = state.onButtonClick
        )
    }
}

@Composable
@Preview
private fun InputDataPagerItemPreview() {
    InputDataPagerItem(
        InputDataPagerItemState(
            title = "Water",
            image = R.drawable.check,
            input = TextFieldValue(""),
            isInputError = false,
            keyboardType = KeyboardType.Number,
            onKeyboardAction = { },
            onInputChange = { },
            onButtonClick = { },
            buttonText = "Next"
        )
    )
}