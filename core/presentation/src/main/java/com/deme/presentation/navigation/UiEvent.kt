package com.deme.presentation.navigation

/**
 * All type of events that we will like to send from viewModels to composables
 */
sealed class UiEvent {
    data class Navigate(val route: String): UiEvent()
    object NavigateUp: UiEvent()
}