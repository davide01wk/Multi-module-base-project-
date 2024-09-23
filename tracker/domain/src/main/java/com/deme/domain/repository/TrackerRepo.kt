package com.deme.domain.repository

import com.deme.domain.model.TrackableFood
import com.deme.domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepo {
    suspend fun searchFood(query: String, page: Int, pageSize: Int): Result<List<TrackableFood>>

    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>>
}