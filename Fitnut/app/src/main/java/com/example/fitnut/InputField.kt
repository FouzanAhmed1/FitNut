package com.example.fitnut

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun InputField(label: String, isPassword: Boolean = false) {
    val textState = remember { mutableStateOf("") }

    BasicTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None, // Change here
        keyboardOptions = if (isPassword) KeyboardOptions.Default else KeyboardOptions.Default,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp)
    )
}
