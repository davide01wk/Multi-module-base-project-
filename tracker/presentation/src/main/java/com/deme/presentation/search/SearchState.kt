package com.deme.presentation.search

import com.deme.domain.model.TrackableFood

data class SearchState(
    val foods: List<TrackableFoodUiState>,
    val query: String,
    val isSearching: Boolean,
    val isHintVisible: Boolean
) {

    companion object {
        fun default() = SearchState(
            foods = emptyList(),
            query = "",
            isSearching = false,
            isHintVisible = false
        )
    }

    data class TrackableFoodUiState(
        val food: TrackableFood,
        val isExpanded: Boolean,
        val amount: String,
    )
}