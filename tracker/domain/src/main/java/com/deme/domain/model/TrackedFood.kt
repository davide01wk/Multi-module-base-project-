package com.deme.domain.model


data class TrackedFood(
    val name: String,
    val imageUrl: String?,
    val amount: Double,
    val kcal: Int,
    val carb: Double,
    val protein: Double,
    val fat: Double,
    val mealType: MealType
)
