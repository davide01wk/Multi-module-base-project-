package com.deme.data.mappers

import com.deme.data.local.entity.TrackedFoodEntity
import com.deme.domain.model.MealType
import com.deme.domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toDomain() = TrackedFood(
    name = this.name,
    carbs = this.carbs,
    protein = this.protein,
    fat = this.fat,
    imageUrl = this.imageUrl,
    mealType = MealType.fromString(this.mealType),
    amount = this.amount,
    date = LocalDate.of(this.year,this.month,this.dayOfMonth),
    calories = this.calories,
    id = this.id
)

fun List<TrackedFoodEntity>.toDomainList(): List<TrackedFood> = this.map {
    it.toDomain()
}

fun TrackedFood.toEntity() = TrackedFoodEntity(
    name = this.name,
    carbs = this.carbs,
    protein = this.protein,
    fat = this.fat,
    imageUrl = this.imageUrl,
    mealType = this.mealType.name,
    amount = this.amount,
    dayOfMonth = this.date.dayOfMonth,
    month = this.date.monthValue,
    year = this.date.year,
    calories = this.calories,
    id = this.id
)