package com.deme.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ProductResponse(
    val nutriments: NutrimentsResponse,
    val product_name: String?,
    val image_front_url: String?
){
    @JsonClass(generateAdapter = true)
    data class NutrimentsResponse(
        val carbohydrates_100g: Double,
        @Json(name = "energy-kcal_100g")
        val kcal_100g: Double,
        val fat_100g: Double,
        val proteins_100g: Double
    )
}
