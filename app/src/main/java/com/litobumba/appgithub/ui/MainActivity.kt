package com.litobumba.appgithub.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.litobumba.appgithub.ui.InitialScreen
import com.litobumba.appgithub.ui.list_user.ListUserScreen
import com.litobumba.appgithub.ui.theme.AppGitHubTheme
import com.litobumba.appgithub.ui.user_detail.UserDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppGitHubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = Screen.INITIAL_SCREEN.name){
                        composable(route = Screen.INITIAL_SCREEN.name){
                            InitialScreen{
                                navController.navigate(Screen.LIST_USER.name)
                            }
                        }

                        composable(route = Screen.LIST_USER.name){
                            ListUserScreen{
                                navController.navigate(Screen.USER_DETAIL.name)
                            }
                        }

                        composable(route = Screen.USER_DETAIL.name){
                            UserDetailScreen{
                                navController.navigate(Screen.LIST_USER.name)
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class Screen {
    INITIAL_SCREEN,
    LIST_USER,
    USER_DETAIL
}
