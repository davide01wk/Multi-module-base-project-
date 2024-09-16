package com.deme.presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deme.core.presentation.R
import com.deme.domain.preferences.Preferences
import com.deme.domain.weight.usecase.SetDefaultWeightFromGender
import com.deme.presentation.navigation.Route
import com.deme.presentation.util.UiEvent
import com.deme.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val setDefaultWeightFromGender: SetDefaultWeightFromGender
) : ViewModel() {

    var weight by mutableStateOf("80")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        setDefaultWeight()
    }

    private fun setDefaultWeight() {
        weight = setDefaultWeightFromGender()
    }

    fun onWeightEnter(weight: String) {
        if (weight.length <= 5) {
            this.weight = weight
        }
    }


    fun onNextClick() {
        viewModelScope.launch {
            val weightNumber = weight.toFloatOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.error_weight_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveWeight(weightNumber)
            _uiEvent.send(UiEvent.Navigate(Route.ACTIVITY))
        }
    }
}