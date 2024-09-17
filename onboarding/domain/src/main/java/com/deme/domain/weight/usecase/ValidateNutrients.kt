package com.deme.domain.weight.usecase

import com.deme.core.presentation.R
import com.deme.presentation.util.UiText

class ValidateNutrients {
    operator fun invoke(
        carbRatio: String,
        proteinRatio: String,
        fatRatio: String,
    ): Result {
        val carbRatioInt = carbRatio.toIntOrNull()
        val proteinRatioInt = proteinRatio.toIntOrNull()
        val fatRatioInt = fatRatio.toIntOrNull()
        if(carbRatioInt == null || proteinRatioInt == null || fatRatioInt == null){
            return Result.Failure(UiText.StringResource(R.string.error_invalid_values))
        }
        if((carbRatioInt + proteinRatioInt + proteinRatioInt) != 100){
            return Result.Failure(UiText.StringResource(R.string.error_not_100_percent))
        }
        return Result.Success(
            carbRatio = carbRatioInt / 100f,
            proteinRatio = proteinRatioInt / 100f,
            fatRatio = fatRatioInt / 100f
        )
    }
}

sealed class Result{
    data class Success(val carbRatio: Float, val proteinRatio: Float, val fatRatio: Float): Result()
    data class Failure(val message: UiText): Result()
}