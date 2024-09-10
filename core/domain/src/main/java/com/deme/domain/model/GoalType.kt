package com.deme.domain.model

import com.deme.domain.model.exception.ParsingException

sealed class GoalType(val name: String) {
    object LoseWeight: GoalType(name = "lose_weight")
    object KeepWeight: GoalType(name = "keep_weight")
    object GainWeight: GoalType(name = "gain_weight")

    companion object {
        fun fromString(name: String): GoalType {
            return when(name) {
                "lose_weight" -> LoseWeight
                "keep_weight" -> KeepWeight
                "gain_weight" -> GainWeight
                else -> throw  ParsingException("Invalid goalType: $name")
            }
        }
    }
}