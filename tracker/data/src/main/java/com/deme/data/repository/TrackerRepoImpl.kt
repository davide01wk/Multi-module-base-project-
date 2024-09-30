package com.deme.data.repository

import com.deme.data.local.TrackerDao
import com.deme.data.mappers.toDomainList
import com.deme.data.mappers.toEntity
import com.deme.data.mappers.toTrackableFoodList
import com.deme.data.remote.OpenFoodApi
import com.deme.domain.model.TrackableFood
import com.deme.domain.model.TrackedFood
import com.deme.domain.repository.TrackerRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TrackerRepoImpl @Inject constructor(
    private val trackerDao: TrackerDao,
    private val foodApi: OpenFoodApi
) : TrackerRepo {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = foodApi.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(
                searchDto.productList
                    .filter {
                        val caloriesPer100g = it.nutriments.kcal_100g
                        val fatPer100g = it.nutriments.fat_100g
                        val carbsPer100g = it.nutriments.carbohydrates_100g
                        val proteinPer100g = it.nutriments.proteins_100g

                        val caloriesPer100gAccepted = fatPer100g * 9 + proteinPer100g * 4 + carbsPer100g * 4
                        val lowerBound = caloriesPer100gAccepted * 0.9
                        val upperBound = caloriesPer100gAccepted * 1.1

                        caloriesPer100g in (lowerBound..upperBound)
                    }
                    .toTrackableFoodList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        trackerDao.insertTrackedFood(trackedFoodEntity = food.toEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        trackerDao.deleteTrackedFood(trackedFoodEntity = food.toEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return trackerDao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entityList ->
            entityList.toDomainList()
        }
    }
}