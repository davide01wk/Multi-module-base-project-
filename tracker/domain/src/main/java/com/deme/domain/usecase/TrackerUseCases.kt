package com.deme.domain.usecase

data class TrackerUseCases(
    val trackFood: TrackFood,
    val deleteFood: DeleteFood,
    val searchFood: SearchFood,
    val getFoodsForDate: GetFoodsForDate,
    val calculateMealNutrients: CalculateMealNutrients
)
