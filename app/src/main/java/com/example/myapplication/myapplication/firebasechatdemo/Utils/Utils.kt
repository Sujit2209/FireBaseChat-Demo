package com.example.myapplication.myapplication.firebasechatdemo.Utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.myapplication.myapplication.firebasechatdemo.DestinationScreen
import com.example.myapplication.myapplication.firebasechatdemo.ViewModel.MainViewModel

fun navigateTo(navController: NavController, route :String)
{
    navController.navigate(route){
        popUpTo(route)
        launchSingleTop = true
    }
}

@Composable
fun CommonProgressBar()
{
    Row(modifier = Modifier
        .alpha(0.5f)
        .background(Color.LightGray)
        .clickable(enabled = true) {}
        .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {

        CircularProgressIndicator()
    }
}

@Composable
fun CheckSignIn(viewModel: MainViewModel,navController: NavController)
{

    val alreadySignedIn = remember {
        mutableStateOf(false)
    }

    val signIn = viewModel.signIn.value

    if(signIn && !alreadySignedIn.value)
    {
        alreadySignedIn.value = true
        navController.navigate((DestinationScreen.Chat.route)){
            popUpTo(0)
        }
    }
}


