package com.deme.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.deme.calorytracker.navigation.navigate
import com.deme.domain.preferences.Preferences
import com.deme.presentation.activity.ActivityLevelRoute
import com.deme.presentation.age.AgeRoute
import com.deme.presentation.gender.GenderRoute
import com.deme.presentation.goal.GoalRoute
import com.deme.presentation.height.HeightRoute
import com.deme.presentation.navigation.Route
import com.deme.presentation.nutrient_goal.NutrientGoalRoute
import com.deme.presentation.search.SearchRoute
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.tracker_overview.TrackerOverviewRoute
import com.deme.presentation.weight.WeightRoute
import com.deme.presentation.welcome.WelcomeRoute
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

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
                        startDestination =
                        if (preferences.loadShowOnboarding()) Route.WELCOME
                        else Route.TRACKER_OVERVIEW
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeRoute(
                                onNavigateToGender = navController::navigate
                            )
                        }
                        composable(Route.AGE) {
                            AgeRoute(
                                scaffoldState = scaffoldState,
                                onNavigateToHeight = navController::navigate
                            )
                        }
                        composable(Route.GENDER) {
                            GenderRoute(
                                onNavigateToAge = navController::navigate
                            )
                        }
                        composable(Route.HEIGHT) {
                            HeightRoute(
                                scaffoldState = scaffoldState,
                                onNavigateToWeight = navController::navigate
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightRoute(
                                scaffoldState = scaffoldState,
                                onNavigateToActivityLevel = navController::navigate
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalRoute(
                                scaffoldState = scaffoldState,
                                onNavigateToTracker = navController::navigate
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityLevelRoute(
                                onNavigateToGoal = navController::navigate
                            )
                        }
                        composable(Route.GOAL) {
                            GoalRoute(
                                onNavigateToNutrientGoal = navController::navigate
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewRoute(
                                onNavigateToSearch = navController::navigate
                            )
                        }
                        composable(
                            route = Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument("mealName") {
                                    type = NavType.StringType
                                },
                                navArgument("dayOfMonth") {
                                    type = NavType.IntType
                                },
                                navArgument("month") {
                                    type = NavType.IntType
                                },
                                navArgument("year") {
                                    type = NavType.IntType
                                },
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName")!!
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!
                            SearchRoute(
                                mealName = mealName,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year,
                                navigateUp = {
                                    navController.navigateUp()
                                },
                                scaffoldState = scaffoldState
                            )
                        }
                    }
                }
            }
        }
    }
}