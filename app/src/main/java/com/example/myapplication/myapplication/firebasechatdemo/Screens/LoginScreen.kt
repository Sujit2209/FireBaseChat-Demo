package com.example.myapplication.myapplication.firebasechatdemo.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.myapplication.firebasechatdemo.Components.HeadingText
import com.example.myapplication.myapplication.firebasechatdemo.Components.NormalText
import com.example.myapplication.myapplication.firebasechatdemo.DestinationScreen
import com.example.myapplication.myapplication.firebasechatdemo.Utils.CheckSignIn
import com.example.myapplication.myapplication.firebasechatdemo.Utils.CommonProgressBar
import com.example.myapplication.myapplication.firebasechatdemo.Utils.navigateTo
import com.example.myapplication.myapplication.firebasechatdemo.ViewModel.MainViewModel
import com.example.myapplication.myapplication.firebasechatdemo.ui.theme.PrimaryColor
import com.example.myapplication.myapplication.firebasechatdemo.ui.theme.SecondaryColor
import com.example.myapplication.myapplication.firebasechatdemo.ui.theme.componentShapes

@Composable
fun LoginScreen(navController: NavController,viewModel: MainViewModel) {

    CheckSignIn(viewModel,navController)



    val emailText = remember {
        mutableStateOf(TextFieldValue())
    }

    val passwordText = remember {
        mutableStateOf(TextFieldValue())
    }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(30.dp))
            HeadingText(value = "Login")
            Spacer(modifier = Modifier.height(20.dp))


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(componentShapes.small),
                label = { Text(text = "Email")},
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryColor,
                    focusedLabelColor = PrimaryColor,
                    cursorColor = PrimaryColor
                ),
                keyboardOptions = KeyboardOptions.Default,
                value = emailText.value,
                onValueChange = {
                    emailText.value  = it
                })


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(componentShapes.small),
                label = { Text(text = "Password")},
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryColor,
                    focusedLabelColor = PrimaryColor,
                    cursorColor = PrimaryColor
                ),
                keyboardOptions = KeyboardOptions.Default,
                value = passwordText.value,
                onValueChange = {
                    passwordText.value  = it
                })



            Spacer(modifier = Modifier.height(80.dp))
            Button(
                onClick = { viewModel.LogIn(emailText.value.text,passwordText.value.text)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(50.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(50.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(SecondaryColor, PrimaryColor)),
                            shape = RoundedCornerShape(50.dp)
                        ),
                    contentAlignment = Alignment.Center,
                )
                {
                    Text(text = "Log In",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)

                }
            }

            Text(text = "New User? Go to Sign Up ->",
                color= PrimaryColor,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navigateTo(navController, DestinationScreen.Signup.route)
                    })
        }

    if(viewModel.inProcess.value){
        CommonProgressBar()
    }
}
