package com.deme.domain.weight.usecase

import com.deme.domain.model.Gender
import com.deme.domain.preferences.Preferences
import javax.inject.Inject

private const val DEFAULT_WEIGHT_MALE = "80"
private const val DEFAULT_WEIGHT_FEMALE = "60"

class SetDefaultWeightFromGender @Inject constructor(
    private val preferences: Preferences
) {
    operator fun invoke(): String {
        val userInfo = preferences.loadUserInfo()
        return if (userInfo.gender == Gender.Male)
            DEFAULT_WEIGHT_MALE else DEFAULT_WEIGHT_FEMALE
    }
}