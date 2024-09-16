package com.deme.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deme.calorytracker.navigation.navigate
import com.deme.presentation.age.AgeRoute
import com.deme.presentation.age.AgeScreen
import com.deme.presentation.gender.GenderRoute
import com.deme.presentation.util.UiEvent
import com.deme.presentation.navigation.Route
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalSpacing
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
                                onNavigateToGender = {
                                    navController.navigate(UiEvent.Navigate(route = Route.GENDER))
                                }
                            )
                        }
                        composable(Route.AGE){
                            AgeRoute(
                                scaffoldState = scaffoldState,
                                onNavigateToHeight = { navController.navigate(UiEvent.Navigate(route = Route.HEIGHT)) }
                            )
                        }
                        composable(Route.GENDER){
                            GenderRoute(
                                onNavigateToAge = { navController.navigate(UiEvent.Navigate(route = Route.AGE)) }
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
}