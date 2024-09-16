package com.deme.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deme.calorytracker.navigation.navigate
import com.deme.presentation.activity.ActivityLevelRoute
import com.deme.presentation.age.AgeRoute
import com.deme.presentation.gender.GenderRoute
import com.deme.presentation.goal.GoalRoute
import com.deme.presentation.height.HeightRoute
import com.deme.presentation.navigation.Route
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.weight.WeightRoute
import com.deme.presentation.welcome.WelcomeRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) { contentPadding ->
                    NavHost(
                        modifier = Modifier.padding(contentPadding),
                        navController = navController,
                        startDestination = Route.WELCOME
                    ) {
                        composable(Route.WELCOME){
                            WelcomeRoute(
                                onNavigateToGender = navController::navigate
                            )
                        }
                        composable(Route.AGE){
                            AgeRoute(
                                scaffoldState = scaffoldState,
                                onNavigateToHeight = navController::navigate
                            )
                        }
                        composable(Route.GENDER){
                            GenderRoute(
                                onNavigateToAge = navController::navigate
                            )
                        }
                        composable(Route.HEIGHT){
                            HeightRoute(
                                scaffoldState = scaffoldState,
                                onNavigateToWeight = navController::navigate
                            )
                        }
                        composable(Route.WEIGHT){
                            WeightRoute(
                                scaffoldState = scaffoldState,
                                onNavigateToActivityLevel = navController::navigate
                            )
                        }
                        composable(Route.NUTRIENT_GOAL){

                        }
                        composable(Route.ACTIVITY){
                            ActivityLevelRoute(
                                onNavigateToGoal = navController::navigate
                            )
                        }
                        composable(Route.GOAL){
                            GoalRoute(
                                onNavigateToActivityLevel = navController::navigate
                            )
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
}