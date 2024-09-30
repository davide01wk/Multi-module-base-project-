package com.deme.presentation.tracker_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deme.domain.preferences.Preferences
import com.deme.domain.usecase.TrackerUseCases
import com.deme.presentation.navigation.Route
import com.deme.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    preferences: Preferences
) : ViewModel() {

    var state by mutableStateOf(TrackerOverviewState.default())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getFoodsForDateJob: Job? = null

    init {
        refreshFoods()
        preferences.saveShowOnboarding(showOnboarding = false)
    }


    private fun refreshFoods() {
        getFoodsForDateJob?.cancel()
        getFoodsForDateJob = trackerUseCases
            .getFoodsForDate(state.date)
            .onEach { trackedFood ->
                val result = trackerUseCases.calculateMealNutrients(trackedFood)
                state = state.copy(
                    caloriesGoal = result.caloriesGoal,
                    carbsGoal = result.carbsGoal,
                    proteinGoal = result.proteinGoal,
                    fatGoal = result.fatGoal,
                    totalCalories = result.totalCalories,
                    totalCarbs = result.totalCarbs,
                    totalProtein = result.totalProtein,
                    totalFat = result.totalFat,
                    meals = state.meals.map { mealState ->
                        val nutrientsForMeal = result.mealNutrients[mealState.mealType]
                        nutrientsForMeal?.let {
                            mealState.copy(
                                mealCarbs = nutrientsForMeal.carbs,
                                mealProteins = nutrientsForMeal.protein,
                                mealFats = nutrientsForMeal.fat,
                                mealCalories = nutrientsForMeal.calories,
                                isExpanded = false,
                                trackedFood = trackedFood.filter { it.mealType == mealState.mealType }
                            )
                        } ?: MealState.default(mealState.mealType)
                    }
                )
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: TrackerOverviewEvent) {
        when (event) {
            is TrackerOverviewEvent.OnAddFoodClick -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Navigate(
                            Route.SEARCH
                                    + "/${event.mealType.name}"
                                    + "/${state.date.dayOfMonth}"
                                    + "/${state.date.monthValue}"
                                    + "/${state.date.year}"
                        )
                    )
                }
            }

            is TrackerOverviewEvent.OnDeleteTrackedFood -> {
                viewModelScope.launch {
                    trackerUseCases.deleteFood(food = event.food)
                    refreshFoods()
                }
            }

            is TrackerOverviewEvent.OnMealExpand -> {
                state = state.copy(
                    meals = state.meals.map {
                        if (it.mealType == event.mealType) {
                            it.copy(
                                isExpanded = !it.isExpanded
                            )
                        } else it
                    }
                )
            }

            TrackerOverviewEvent.OnNextDayClick -> {
                state = state.copy(date = state.date.plusDays(1))
                refreshFoods()
            }

            TrackerOverviewEvent.OnPreviousDayClick -> {
                state = state.copy(date = state.date.minusDays(1))
                refreshFoods()
            }
        }
    }
}



