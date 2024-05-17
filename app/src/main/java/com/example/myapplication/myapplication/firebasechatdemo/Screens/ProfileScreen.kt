package com.example.myapplication.myapplication.firebasechatdemo.Screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.myapplication.myapplication.firebasechatdemo.ViewModel.MainViewModel

@Composable
fun ProfileScreen(navController: NavController,viewModel: MainViewModel)
{

    BottomNavigation(selecteditem = BottomNavigation.PROFILE, navController = navController)

}