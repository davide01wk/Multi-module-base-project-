package com.deme.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deme.calorytracker.navigation.navigate
import com.deme.presentation.gender.GenderRoute
import com.deme.presentation.util.UiEvent
import com.deme.presentation.navigation.Route
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.welcome.WelcomeRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                        WelcomeRoute(
                            onNavigateToGender = {
                                navController.navigate(UiEvent.Navigate(route = Route.GENDER))
                            }
                        )
                    }
                    composable(Route.AGE){

                    }
                    composable(Route.GENDER){
                        GenderRoute(
                            onNavigate = {

                            }
                        )
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