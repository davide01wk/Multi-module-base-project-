package com.deme.domain.model

import com.deme.domain.model.exception.ParsingException

sealed class Gender(val name: String) {
    object Male: Gender(name = "male")
    object Female: Gender(name = "female")

    companion object {
        fun fromString(name: String): Gender {
            return when(name) {
                "male" -> Male
                "female" -> Female
                else -> throw  ParsingException("Invalid gender: $name")
            }
        }
    }
}