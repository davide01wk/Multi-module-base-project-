package com.deme.data.repository

import com.deme.data.local.TrackerDao
import com.deme.data.mappers.toDomainList
import com.deme.data.mappers.toEntity
import com.deme.data.mappers.toTrackableFoodList
import com.deme.data.remote.OpenFoodApi
import com.deme.domain.model.TrackableFood
import com.deme.domain.model.TrackedFood
import com.deme.repository.TrackerRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TrackerRepoImpl @Inject constructor(
    private val trackerDao: TrackerDao,
    private val api: OpenFoodApi
) : TrackerRepo {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(
                searchDto.toTrackableFoodList()
            )
        } catch (e: Exception) {
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