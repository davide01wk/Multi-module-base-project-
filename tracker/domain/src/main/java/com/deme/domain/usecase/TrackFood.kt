package com.deme.domain.usecase

import com.deme.domain.model.MealType
import com.deme.domain.model.TrackableFood
import com.deme.domain.model.TrackedFood
import com.deme.domain.repository.TrackerRepo
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFood(
    private val repository: TrackerRepo
) {
    suspend operator fun invoke(
        food: TrackableFood,
        mealType: MealType,
        amount: Int,
        date: LocalDate
    ) {
        val trackedFood = TrackedFood(
            name = food.name,
            carbs = (food.carbsPer100g * (amount / 100.0)).roundToInt(),
            protein = (food.proteinPer100g * (amount / 100.0)).roundToInt(),
            fat = (food.fatPer100g * (amount / 100.0)).roundToInt(),
            imageUrl = food.imageUrl,
            mealType = mealType,
            amount = amount,
            date = date,
            calories = (food.caloriesPer100g * (amount / 100.0)).roundToInt()
        )
        repository.insertTrackedFood(
            food = trackedFood
        )
    }
}