package com.deme.presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deme.domain.preferences.Preferences
import com.deme.domain.weight.usecase.Result
import com.deme.domain.weight.usecase.ValidateNutrients
import com.deme.presentation.navigation.Route
import com.deme.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val validateNutrients: ValidateNutrients
): ViewModel() {
    var nutrientGoal by mutableStateOf(NutrientGoalState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent){
        when(event){
            is NutrientGoalEvent.OnCarbRatioEnter -> {
                nutrientGoal = nutrientGoal.copy(
                    carbRatio = event.carbRatio
                )
            }
            is NutrientGoalEvent.OnProteinRatioEnter -> {
                nutrientGoal = nutrientGoal.copy(
                    proteinRatio = event.proteinRatio
                )
            }
            is NutrientGoalEvent.OnFatRatioEnter -> {
                nutrientGoal = nutrientGoal.copy(
                    fatRatio = event.fatRatio
                )
            }
            NutrientGoalEvent.OnNextClick -> {
                val result = validateNutrients(
                    carbRatio = nutrientGoal.carbRatio,
                    proteinRatio = nutrientGoal.proteinRatio,
                    fatRatio = nutrientGoal.fatRatio
                )
                when(result){
                    is Result.Success -> {
                        preferences.saveCarbRatio(result.carbRatio)
                        preferences.saveProteinRatio(result.proteinRatio)
                        preferences.saveFatRatio(result.fatRatio)
                        viewModelScope.launch{
                            _uiEvent.send(
                                UiEvent.Navigate(Route.TRACKER_OVERVIEW)
                            )
                        }
                    }
                    is Result.Failure -> {
                        viewModelScope.launch{
                            _uiEvent.send(
                                UiEvent.ShowSnackbar(message = result.message)
                            )
                        }
                    }
                }
            }
        }
    }
}