package com.deme.domain.model


sealed class MealType(var name: String) {
    object BreakFast: MealType(name = "breakfast")
    object Lunch: MealType(name = "lunch")
    object Dinner: MealType(name = "dinner")
    object Snack: MealType(name = "snack")

    companion object {
        fun fromString(name: String): MealType {
           return when(name){
                "breakfast" -> BreakFast
                "lunch" -> Lunch
                "dinner" -> Dinner
                "snack" -> Snack
                else -> BreakFast
            }
        }
    }
}