package com.deme.presentation.search
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deme.domain.usecase.FilterOutDigits
import com.deme.domain.usecase.TrackerUseCases
import com.deme.presentation.util.UiEvent
import com.deme.presentation.util.UiText
import com.deme.core.presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

    var state by mutableStateOf(SearchState.default())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnAmountFoodChange -> {
                state = state.copy(
                    foods = state.foods.map {
                        if (it.food == event.food) {
                            it.copy(
                                amount = filterOutDigits(event.amount)
                            )
                        } else it
                    }
                )
            }

            is SearchEvent.OnExpandFood -> {
                state = state.copy(
                    foods = state.foods.map {
                        if (it.food == event.food) {
                            it.copy(
                                isExpanded = !it.isExpanded
                            )
                        } else it
                    }
                )
            }

            is SearchEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }

            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }

            SearchEvent.OnSearchFood -> {
                executeSearch(query = state.query)
            }

            is SearchEvent.OnTrackFoodClick -> {
                trackFood(event = event)
            }
        }
    }

    private fun trackFood(event: SearchEvent.OnTrackFoodClick) {
        viewModelScope.launch {
            val foodUiState = state.foods.find { it.food == event.food }
            trackerUseCases.trackFood(
                food = foodUiState?.food ?: return@launch,
                mealType = event.mealType,
                amount = foodUiState.amount.toIntOrNull() ?: return@launch,
                date = event.date
            )
            _uiEvent.send(UiEvent.NavigateUp)
        }
    }

    private fun executeSearch(
        query: String
    ) {
        viewModelScope.launch {
            state = state.copy(isSearching = true)
            trackerUseCases
                .searchFood(query = query)
                .onSuccess { trackableFoodList ->
                    state = state.copy(
                        foods = trackableFoodList.map { food ->
                            SearchState.TrackableFoodUiState(
                                food = food,
                                isExpanded = false,
                                amount = ""
                            )
                        },
                        isSearching = false
                    )
                }.onFailure {
                    state = state.copy(isSearching = false)
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            UiText.StringResource(R.string.error_something_went_wrong)
                        )
                    )
                }
        }
    }
}


