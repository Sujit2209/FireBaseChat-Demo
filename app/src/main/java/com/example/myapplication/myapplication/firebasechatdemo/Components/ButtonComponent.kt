package com.example.myapplication.myapplication.firebasechatdemo.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.myapplication.firebasechatdemo.ui.theme.PrimaryColor
import com.example.myapplication.myapplication.firebasechatdemo.ui.theme.SecondaryColor

//@Composable
//fun ButtonComponent(value:String)
//{
//    Button(
//        onClick = { /*TODO*/ },
//        modifier = Modifier
//            .fillMaxWidth()
//            .heightIn(50.dp),
//        contentPadding = PaddingValues(),
//        colors = ButtonDefaults.buttonColors(Color.Transparent)
//        ) {
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .heightIn(50.dp)
//                .background(
//                    brush = Brush.horizontalGradient(listOf(SecondaryColor, PrimaryColor)),
//                    shape = RoundedCornerShape(50.dp)
//                ),
//            contentAlignment = Alignment.Center,
//        )
//        {
//            Text(text = value,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold)
//
//        }
//    }
//}