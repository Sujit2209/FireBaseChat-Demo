package com.example.myapplication.myapplication.firebasechatdemo.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.myapplication.myapplication.firebasechatdemo.ui.theme.PrimaryColor
import com.example.myapplication.myapplication.firebasechatdemo.ui.theme.componentShapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(labelValue : String) {
    val text = remember {
        mutableStateOf("")
    }

//    OutlinedTextField(label = { Text(text = labelValue) },
//
//        colors = OutlinedTextFieldDefaults.colors(
//            focusedBorderColor = PrimaryColor,
//            focusedLabelColor = PrimaryColor,
//            cursorColor = PrimaryColor
//        ),
//        keyboardOptions = KeyboardOptions.Default,
//        value = text,
//        onValueChange = {
//            text.value = it
//        },)
//    {}

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().clip(componentShapes.small),
        label = { Text(text = labelValue)},
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryColor,
            focusedLabelColor = PrimaryColor,
            cursorColor = PrimaryColor
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = text.value,
        onValueChange = {
            text.value  = it
        })

}