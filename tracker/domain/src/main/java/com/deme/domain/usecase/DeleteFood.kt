package com.deme.domain.usecase

import com.deme.domain.model.TrackedFood
import com.deme.domain.repository.TrackerRepo

class DeleteFood(
    private val repository: TrackerRepo
) {
    suspend operator fun invoke(food: TrackedFood) {
        repository.deleteTrackedFood(food = food)
    }
}