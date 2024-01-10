package com.rohitneel.tweetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rohitneel.tweetapp.screens.CategoryScreen
import com.rohitneel.tweetapp.screens.DetailScreen
import com.rohitneel.tweetapp.ui.theme.TweetsyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TweetsyTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "TweetsApp")
                        }, backgroundColor = Color.Black, contentColor = Color.White)
                    }
                ) {
                    Box(modifier = Modifier.padding(it)){
                        App()
                    }
                }
                
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category"){
        composable(route = "category"){
            CategoryScreen {
                navController.navigate("detail/${it}")
            }
        }
        composable(route = "detail/{category}",
        arguments = listOf(
            navArgument("category"){
                type = NavType.StringType
            }
        )
        ){
            DetailScreen()
        }
    }
}