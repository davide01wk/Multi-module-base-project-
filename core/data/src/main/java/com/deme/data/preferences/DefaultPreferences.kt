package com.deme.data.preferences

import android.content.SharedPreferences
import com.deme.domain.model.ActivityLevel
import com.deme.domain.model.Gender
import com.deme.domain.model.GoalType
import com.deme.domain.model.UserInfo
import com.deme.domain.preferences.Preferences


class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences {

    companion object {
        private const val KEY_GENDER = "gender"
        private const val KEY_AGE = "age"
        private const val KEY_WEIGHT = "weight"
        private const val KEY_HEIGHT = "height"
        private const val KEY_ACTIVITY_LEVEL = "activity_level"
        private const val KEY_GOAL_TYPE = "goal_type"
        private const val KEY_CARB_RATIO = "carb_ratio"
        private const val KEY_PROTEIN_RATIO = "protein_ratio"
        private const val KEY_FAT_RATIO = "fat_ratio"
    }

    override fun saveGender(gender: Gender) {
        sharedPref.edit()
            .putString(KEY_GENDER, gender.name)
            .apply()
    }

    override fun saveAge(age: Int) {
        sharedPref.edit()
            .putInt(KEY_AGE, age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit()
            .putFloat(KEY_WEIGHT, weight)
            .apply()
    }

    override fun saveHeight(height: Int) {
        sharedPref.edit()
            .putInt(KEY_HEIGHT, height)
            .apply()
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharedPref.edit()
            .putString(KEY_ACTIVITY_LEVEL, level.name)
            .apply()
    }

    override fun saveGoalType(type: GoalType) {
        sharedPref.edit()
            .putString(KEY_GOAL_TYPE, type.name)
            .apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(KEY_CARB_RATIO, ratio)
            .apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(KEY_PROTEIN_RATIO, ratio)
            .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(KEY_FAT_RATIO, ratio)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        val genderString = sharedPref.getString(KEY_GENDER, null)
        val age = sharedPref.getInt(KEY_AGE, -1)
        val weight = sharedPref.getFloat(KEY_WEIGHT, -1f)
        val height = sharedPref.getInt(KEY_HEIGHT, -1)
        val activityLevelString = sharedPref.getString(KEY_ACTIVITY_LEVEL, null)
        val goalTypeString = sharedPref.getString(KEY_GOAL_TYPE, null)
        val carbRatio = sharedPref.getFloat(KEY_CARB_RATIO, -1f)
        val proteinRatio = sharedPref.getFloat(KEY_PROTEIN_RATIO, -1f)
        val fatRatio = sharedPref.getFloat(KEY_FAT_RATIO, -1f)

        return UserInfo(
            gender = Gender.fromString(genderString ?: "male"),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevelString ?: "medium"),
            goalType = GoalType.fromString(goalTypeString ?: "keep_weight"),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }
}