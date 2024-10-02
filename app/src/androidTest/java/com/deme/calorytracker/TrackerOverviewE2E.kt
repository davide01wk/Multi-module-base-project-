package com.deme.calorytracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.deme.calorytracker.repository.TrackerRepoFakeImpl
import com.deme.domain.model.ActivityLevel
import com.deme.domain.model.Gender
import com.deme.domain.model.GoalType
import com.deme.domain.model.TrackableFood
import com.deme.domain.model.UserInfo
import com.deme.domain.preferences.Preferences
import com.deme.domain.usecase.CalculateMealNutrients
import com.deme.domain.usecase.DeleteFood
import com.deme.domain.usecase.FilterOutDigits
import com.deme.domain.usecase.GetFoodsForDate
import com.deme.domain.usecase.SearchFood
import com.deme.domain.usecase.TrackFood
import com.deme.domain.usecase.TrackerUseCases
import com.deme.presentation.navigation.Route
import com.deme.presentation.search.SearchRoute
import com.deme.presentation.search.SearchViewModel
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.tracker_overview.TrackerOverviewRoute
import com.deme.presentation.tracker_overview.TrackerOverviewViewModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.roundToInt

@HiltAndroidTest
class TrackerOverviewE2E {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var trackerRepoFake: TrackerRepoFakeImpl
    private lateinit var trackerUseCases: TrackerUseCases
    private lateinit var preferences: Preferences
    private lateinit var trackerOverviewViewModel: TrackerOverviewViewModel
    private lateinit var searchViewModel: SearchViewModel

    private lateinit var navController: NavHostController

    @Before
    fun setUp() {
        preferences = mockk<Preferences>(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )
        trackerRepoFake = TrackerRepoFakeImpl()
        trackerUseCases = TrackerUseCases(
            trackFood = TrackFood(trackerRepoFake),
            searchFood = SearchFood(trackerRepoFake),
            getFoodsForDate = GetFoodsForDate(trackerRepoFake),
            deleteFood = DeleteFood(trackerRepoFake),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
        trackerOverviewViewModel = TrackerOverviewViewModel(
            trackerUseCases = trackerUseCases,
            preferences = preferences
        )
        searchViewModel = SearchViewModel(
            trackerUseCases = trackerUseCases,
            filterOutDigits = FilterOutDigits()
        )
        composeRule.setContent {
            CaloryTrackerTheme {
                navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) { contentPadding ->
                    NavHost(
                        modifier = Modifier.padding(contentPadding),
                        navController = navController,
                        startDestination = Route.TRACKER_OVERVIEW
                    ) {
                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewRoute(
                                onNavigateToSearch = { mealName, dayOfMonth, month, year ->
                                    navController.navigate(
                                        route = Route.SEARCH +
                                                "/$mealName/$dayOfMonth/$month/$year"
                                    )
                                }
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

    @Test
    fun addBreakfast_appearsUnderBreakfast_nutrientsProperlyCalculated() {
        val foodResultCaloriesPer100g = 150
        val foodResultProteinPer100g = 5
        val foodResultCarbsPer100g = 50
        val foodResultFatPer100g = 1
        trackerRepoFake.searchResults = listOf(
            TrackableFood(
                name = "banana",
                imageUrl = null,
                caloriesPer100g = foodResultCaloriesPer100g,
                proteinPer100g = foodResultProteinPer100g,
                carbsPer100g = foodResultCarbsPer100g,
                fatPer100g = foodResultFatPer100g
            )
        )
        val addedAmount = 150
        val expectedCalories = (1.5f * foodResultCaloriesPer100g).roundToInt()
        val expectedCarbs = (1.5f * foodResultCarbsPer100g).roundToInt()
        val expectedProtein = (1.5f * foodResultProteinPer100g).roundToInt()
        val expectedFat = (1.5f * foodResultFatPer100g).roundToInt()

        composeRule
            .onNodeWithText("Add Breakfast")
            .assertDoesNotExist()
        composeRule
            .onNodeWithContentDescription("Breakfast")
            .performClick()
        composeRule
            .onNodeWithText("Add Breakfast")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.SEARCH)
        ).isTrue()


        composeRule
            .onNodeWithContentDescription("search_text_field")
            .performTextInput("banana")

        composeRule
            .onNodeWithContentDescription("Search...")
            .performClick()

        composeRule.onRoot().printToLog("COMPOSE TREE")

        composeRule
            .onNodeWithText("Carbs")
            .performClick()
        composeRule
            .onNodeWithContentDescription("amount_text_field")
            .performTextInput(addedAmount.toString())
        composeRule
            .onNodeWithContentDescription("Add")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.TRACKER_OVERVIEW)
        ).isTrue()

        composeRule
            .onNodeWithText(expectedCarbs.toString())
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(expectedProtein.toString())
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(expectedFat.toString())
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(expectedCalories.toString())
            .assertIsDisplayed()

    }
}