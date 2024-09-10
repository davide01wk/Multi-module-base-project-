package com.deme.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deme.calorytracker.navigation.navigate
import com.deme.calorytracker.ui.theme.CaloryTrackerTheme
import com.deme.presentation.navigation.UiEvent
import com.deme.presentation.navigation.util.Route
import com.deme.presentation.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.WELCOME
                ) {
                    composable(Route.WELCOME){
                        WelcomeScreen(
                            onNavigateToAge = {
                                navController.navigate(UiEvent.Navigate(route = Route.AGE))
                            }
                        )
                    }
                    composable(Route.AGE){

                    }
                    composable(Route.GENDER){

                    }
                    composable(Route.HEIGHT){

                    }
                    composable(Route.WEIGHT){

                    }
                    composable(Route.NUTRIENT_GOAL){

                    }
                    composable(Route.ACTIVITY){

                    }
                    composable(Route.GOAL){

                    }
                    composable(Route.TRACKER_OVERVIEW){

                    }
                    composable(Route.SEARCH){

                    }
                }
            }
        }
    }
}