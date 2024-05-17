package com.example.myapplication.myapplication.firebasechatdemo.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.myapplication.firebasechatdemo.DestinationScreen
import com.example.myapplication.myapplication.firebasechatdemo.R
import com.example.myapplication.myapplication.firebasechatdemo.Utils.navigateTo
import com.example.myapplication.myapplication.firebasechatdemo.ui.theme.White

enum class BottomNavigation(val icon:Int, val destinationScreen: DestinationScreen)
{
    CHAT(R.drawable.chat_icon,DestinationScreen.Chat),
    PROFILE(R.drawable.profile_iicon,DestinationScreen.Profile)
}

@Composable
fun BottomNavigation(selecteditem:BottomNavigation,navController: NavController)
{

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .background(White),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        for(item in BottomNavigation.entries)
        {
            Image(painter = painterResource(id = item.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
                    .weight(1f)
                    .clickable {
                        navigateTo(navController,item.destinationScreen.route)
                    },
                colorFilter = if(item ==selecteditem)
            ColorFilter.tint(color = Color.Black)
            else
                ColorFilter.tint(Color.Gray))
        }
    }

}