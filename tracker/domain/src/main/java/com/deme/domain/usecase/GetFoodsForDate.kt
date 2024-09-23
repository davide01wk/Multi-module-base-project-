package com.deme.domain.usecase

import com.deme.domain.model.TrackedFood
import com.deme.domain.repository.TrackerRepo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDate(
    private val repository: TrackerRepo
) {
    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(
            localDate = date
        )
    }
}