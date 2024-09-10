package com.deme.domain.model

import com.deme.domain.model.exception.ParsingException

sealed class ActivityLevel(val name: String) {
    object Low: ActivityLevel(name = "low")
    object Medium: ActivityLevel(name = "medium")
    object High: ActivityLevel(name = "high")

    companion object {
        fun fromString(name: String): ActivityLevel {
            return when(name) {
                "low" -> Low
                "medium" -> Medium
                "high" -> High
                else -> throw  ParsingException("Invalid activityLevel: $name")
            }
        }
    }
}