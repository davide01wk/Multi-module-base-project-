package com.deme.presentation.tracker_overview

import androidx.annotation.DrawableRes
import com.deme.core.presentation.R
import com.deme.domain.model.MealType
import com.deme.domain.model.TrackedFood

data class TrackerOverviewState(
    val caloriesGoal: Int,
    val carbsGoal: Int,
    val proteinGoal: Int,
    val fatGoal: Int,
    val totalCalories: Int,
    val totalCarbs: Int,
    val totalProtein: Int,
    val totalFat: Int,
    val meals: List<MealState>
) {
    companion object {
        fun default() = TrackerOverviewState(
            caloriesGoal = 0,
            carbsGoal = 0,
            fatGoal = 0,
            proteinGoal = 0,
            totalCarbs = 0,
            totalProtein = 0,
            totalFat = 0,
            totalCalories = 0,
            meals = MealState.defaultList()
        )
    }
}

data class MealState(
    val mealType: MealType,
    @DrawableRes val imageRes: Int,
    val mealCarbs: Int,
    val mealProteins: Int,
    val mealFats: Int,
    val mealCalories: Int,
    val isExpanded: Boolean,
    val trackedFood: List<TrackedFood>
) {
    companion object {
        fun defaultList(): List<MealState> = listOf(
            MealState(
                mealType = MealType.BreakFast,
                imageRes = R.drawable.ic_breakfast,
                mealCarbs = 0,
                mealProteins = 0,
                mealFats = 0,
                mealCalories = 0,
                trackedFood = emptyList(),
                isExpanded = false
            ),
            MealState(
                mealType = MealType.Snack,
                imageRes = R.drawable.ic_snack,
                mealCarbs = 0,
                mealProteins = 0,
                mealFats = 0,
                mealCalories = 0,
                trackedFood = emptyList(),
                isExpanded = false
            ),
            MealState(
                mealType = MealType.Lunch,
                imageRes = R.drawable.ic_lunch,
                mealCarbs = 0,
                mealProteins = 0,
                mealFats = 0,
                mealCalories = 0,
                trackedFood = emptyList(),
                isExpanded = false
            ),
            MealState(
                mealType = MealType.Dinner,
                imageRes = R.drawable.ic_dinner,
                mealCarbs = 0,
                mealProteins = 0,
                mealFats = 0,
                mealCalories = 0,
                trackedFood = emptyList(),
                isExpanded = false
            ),
        )
    }
}
