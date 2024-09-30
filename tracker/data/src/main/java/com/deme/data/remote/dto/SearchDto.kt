package com.deme.data.remote.dto

import com.squareup.moshi.Json


data class SearchDto(
    @Json(name = "products")
    val productList: List<ProductResponse>
)
