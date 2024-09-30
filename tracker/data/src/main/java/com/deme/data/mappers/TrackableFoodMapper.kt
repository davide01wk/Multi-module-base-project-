package com.deme.data.mappers

import com.deme.data.remote.dto.ProductResponse
import com.deme.domain.model.TrackableFood

fun List<ProductResponse>.toTrackableFoodList(): List<TrackableFood> {
    return this.map {
        TrackableFood(
            name = it.product_name ?: "",
            imageUrl = it.image_front_url ?: "",
            caloriesPer100g = it.nutriments.kcal_100g.toInt(),
            carbsPer100g = it.nutriments.carbohydrates_100g.toInt(),
            proteinPer100g = it.nutriments.proteins_100g.toInt(),
            fatPer100g = it.nutriments.fat_100g.toInt()
        )
    }
}