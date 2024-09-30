package com.deme.presentation.tracker_overview

import androidx.annotation.DrawableRes
import com.deme.core.presentation.R
import com.deme.domain.model.MealType
import com.deme.domain.model.TrackedFood
import com.deme.presentation.util.UiText
import java.time.LocalDate

data class TrackerOverviewState(
    val date: LocalDate,
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
            date = LocalDate.now(),
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
    val name: UiText,
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
            default(mealType = MealType.BreakFast),
            default(mealType = MealType.Snack),
            default(mealType = MealType.Dinner),
            default(mealType = MealType.Lunch),
        )

        fun default(mealType: MealType): MealState {
            return when (mealType) {
                MealType.BreakFast -> MealState(
                    mealType = MealType.BreakFast,
                    name = UiText.StringResource(R.string.breakfast),
                    imageRes = R.drawable.ic_breakfast,
                    mealCarbs = 0,
                    mealProteins = 0,
                    mealFats = 0,
                    mealCalories = 0,
                    trackedFood = emptyList(),
                    isExpanded = false
                )

                MealType.Snack -> MealState(
                    mealType = MealType.Snack,
                    name = UiText.StringResource(R.string.snacks),
                    imageRes = R.drawable.ic_snack,
                    mealCarbs = 0,
                    mealProteins = 0,
                    mealFats = 0,
                    mealCalories = 0,
                    trackedFood = emptyList(),
                    isExpanded = false
                )

                MealType.Lunch -> MealState(
                    mealType = MealType.Lunch,
                    name = UiText.StringResource(R.string.lunch),
                    imageRes = R.drawable.ic_lunch,
                    mealCarbs = 0,
                    mealProteins = 0,
                    mealFats = 0,
                    mealCalories = 0,
                    trackedFood = emptyList(),
                    isExpanded = false
                )

                MealType.Dinner -> MealState(
                    mealType = MealType.Dinner,
                    name = UiText.StringResource(R.string.dinner),
                    imageRes = R.drawable.ic_dinner,
                    mealCarbs = 0,
                    mealProteins = 0,
                    mealFats = 0,
                    mealCalories = 0,
                    trackedFood = emptyList(),
                    isExpanded = false
                )
            }
        }
    }
}
