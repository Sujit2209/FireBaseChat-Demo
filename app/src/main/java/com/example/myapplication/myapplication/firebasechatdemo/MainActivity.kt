package com.example.myapplication.myapplication.firebasechatdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.internal.GeneratedComponent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.myapplication.firebasechatdemo.Screens.ChatScreen
import com.example.myapplication.myapplication.firebasechatdemo.Screens.LoginScreen
import com.example.myapplication.myapplication.firebasechatdemo.Screens.ProfileScreen
import com.example.myapplication.myapplication.firebasechatdemo.Screens.SignUpScreen
import com.example.myapplication.myapplication.firebasechatdemo.ViewModel.MainViewModel
import com.example.myapplication.myapplication.firebasechatdemo.ui.theme.FireBaseChatDemoTheme
import dagger.hilt.android.AndroidEntryPoint

sealed class DestinationScreen(var route: String)
{
    object Signup : DestinationScreen("signup")
    object Login : DestinationScreen("login")
    object Chat : DestinationScreen("chatList")

    object DirectChat : DestinationScreen("singleChat/{chatID}"){
        fun createRoute(id:String) = "singlechat/$id"
    }

    object Profile : DestinationScreen("signup")

}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FireBaseChatDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }

    @Composable
    fun AppNavigation()
    {

        val navController = rememberNavController()
        var viewModel = hiltViewModel<MainViewModel>()
        NavHost(navController = navController, startDestination = DestinationScreen.Signup.route){

            composable(DestinationScreen.Signup.route)
            {
                SignUpScreen(navController,viewModel)
            }

            composable(DestinationScreen.Login.route)
            {
                LoginScreen(navController,viewModel)
            }

            composable(DestinationScreen.Chat.route)
            {
                ChatScreen(navController,viewModel)
            }
            composable(DestinationScreen.Profile.route)
            {
                ProfileScreen(navController,viewModel)
            }


        }

    }
}

