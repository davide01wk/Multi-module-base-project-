package com.deme.presentation.util

/**
 * All type of events that we will like to send from viewModels to composables
 */
sealed class UiEvent {
    data class Navigate(val route: String): UiEvent()
    object NavigateUp: UiEvent()
    data class ShowSnackbar(val message: UiText): UiEvent()
}
