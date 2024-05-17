package com.example.myapplication.myapplication.firebasechatdemo.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.myapplication.firebasechatdemo.Utils.CommonProgressBar
import com.example.myapplication.myapplication.firebasechatdemo.ViewModel.MainViewModel
import com.example.myapplication.myapplication.firebasechatdemo.ui.theme.SecondaryColor


@Composable
fun ChatScreen(navController: NavController,viewModel: MainViewModel)
{

    val inProgress = viewModel.inProcessChat
    if(inProgress.value){
        CommonProgressBar()
    }else{

        val chats = viewModel.chats.value
        val userData = viewModel.userData.value
        val showDialog = remember {
            mutableStateOf(false)
        }
        val onFabClick:()->Unit={showDialog.value=true}
        val onDismiss:()->Unit={showDialog.value=false}
        val onAddChat:(String)-> Unit = {
            viewModel.onAddChat(it)
        }

        Scaffold(
            floatingActionButton = {
                FAB(
                    showDialog = showDialog.value,
                    onFabClick = onFabClick,
                    onDismiss = onDismiss,
                    onAddChat = onAddChat
                )
            },

            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                }
            }
        )

    }

    BottomNavigation(selecteditem = BottomNavigation.CHAT, navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAB(
    showDialog: Boolean,
    onFabClick:()-> Unit,
    onDismiss:()-> Unit,
    onAddChat: (String)-> Unit
)
{
    val addChatNumber = remember {
        mutableStateOf("")
    }

    if(showDialog)
    {
        AlertDialog(onDismissRequest = {
                                       onDismiss.invoke()
            addChatNumber.value=""
        }, confirmButton = {
            Button(onClick = {
                onAddChat(addChatNumber.value)
            }) {
                Text(text = "Add Chat")
            }
        },
            title = { Text(text = "Add Chat")},
            text = {
                OutlinedTextField(value = addChatNumber.value, onValueChange = {addChatNumber.value=it})
            })

        FloatingActionButton(onClick = {
            onFabClick
        },
            containerColor = MaterialTheme.colorScheme.secondary,
            shape = CircleShape,
            modifier = Modifier.padding(bottom = 50.dp)
            ) {
        
            Icon(Icons.Filled.Add,"" )
        }
    }

}
