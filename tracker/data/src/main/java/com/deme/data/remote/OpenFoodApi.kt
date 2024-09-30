package com.deme.data.remote

import com.deme.data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenFoodApi {

    @GET("cgi/search.pl?search_simple=1&action=process&json=1&&fields=product_name,image_front_url,nutriments")
    suspend fun searchFood(
        @Query("search_terms") query: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): SearchDto


    companion object {
        const val BASE_URL = "https://world.openfoodfacts.org/"
    }

}