package com.deme.domain.usecase

import com.deme.domain.model.TrackableFood
import com.deme.domain.repository.TrackerRepo
import kotlin.Result
class SearchFood(
    private val repository: TrackerRepo
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> {
        return if(query.isBlank())
            Result.success(emptyList())
        else repository.searchFood(
            query = query.trim(), page = page, pageSize = pageSize
        )
    }
}