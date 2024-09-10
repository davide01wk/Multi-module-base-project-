package com.deme.domain.preferences

import com.deme.domain.model.ActivityLevel
import com.deme.domain.model.Gender
import com.deme.domain.model.GoalType

interface Preferences {
    fun saveGender(gender: Gender)
    fun saveAge(age: Int)
    fun saveWeight(weight: Float)
    fun saveHeight(height: Float)
    fun saveActivityLevel(level: ActivityLevel)
    fun saveGoalType(type: GoalType)
    fun saveCarbRatio(ratio: Float)
    fun saveProteinRatio(ratio: Float)
    fun saveFatRatio(ratio: Float)

    fun loadUserInfo()

}