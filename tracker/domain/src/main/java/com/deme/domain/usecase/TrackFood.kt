package com.deme.domain.usecase

import com.deme.domain.model.MealType
import com.deme.domain.model.TrackableFood
import com.deme.domain.model.TrackedFood
import com.deme.domain.repository.TrackerRepo
import java.time.LocalDate

class TrackFood(
    private val repository: TrackerRepo
) {
    suspend operator fun invoke(
        food: TrackableFood,
        mealType: MealType,
        amount: Int
    ) {
        val trackedFood = TrackedFood(
            name = food.name,
            carbs = food.carbsPer100g * (amount / 100),
            protein = food.proteinPer100g * (amount / 100),
            fat = food.fatPer100g * (amount / 100),
            imageUrl = food.imageUrl,
            mealType = mealType,
            amount = amount,
            date = LocalDate.now(),
            calories = food.caloriesPer100g * (amount / 100)
        )
        repository.insertTrackedFood(
            food = trackedFood
        )
    }
}