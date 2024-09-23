package com.deme.domain.preferences

import com.deme.domain.model.ActivityLevel
import com.deme.domain.model.Gender
import com.deme.domain.model.GoalType
import com.deme.domain.model.UserInfo

interface Preferences {
    fun saveGender(gender: Gender)
    fun saveAge(age: Int)
    fun saveWeight(weight: Float)
    fun saveHeight(height: Int)
    fun saveActivityLevel(level: ActivityLevel)
    fun saveGoalType(type: GoalType)
    fun saveCarbRatio(ratio: Float)
    fun saveProteinRatio(ratio: Float)
    fun saveFatRatio(ratio: Float)

    fun saveShowOnboarding(showOnboarding: Boolean)
    fun loadShowOnboarding(): Boolean

    fun loadUserInfo(): UserInfo

}