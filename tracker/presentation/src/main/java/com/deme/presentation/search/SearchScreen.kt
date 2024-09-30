package com.deme.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deme.core.presentation.R
import com.deme.domain.model.MealType
import com.deme.presentation.search.component.SearchTextField
import com.deme.presentation.search.component.TrackableFoodCard
import com.deme.presentation.theme.LocalFontSize
import com.deme.presentation.theme.LocalSpacing
import com.deme.presentation.util.UiEvent
import java.time.LocalDate

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    navigateUp: () -> Unit,
    mealName: String,
    dayOfMonth: Int,
    month: Int,
    year: Int
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(keyboardController) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateUp -> navigateUp()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                    keyboardController?.hide()
                }

                else -> Unit
            }
        }
    }

    SearchScreen(
        keyboardController = keyboardController,
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        mealName = mealName,
        dayOfMonth = dayOfMonth,
        month = month,
        year = year
    )
}

@Composable
fun SearchScreen(
    keyboardController:  SoftwareKeyboardController?,
    state: SearchState,
    mealName: String,
    dayOfMonth: Int,
    month: Int,
    year: Int,
    onEvent: (SearchEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(LocalSpacing.current.md)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.add_meal, mealName),
            style = MaterialTheme.typography.h2,
            fontSize = LocalFontSize.current.fontSize28
        )
        SearchTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.query,
            onSearch = {
                keyboardController?.hide()
                onEvent(SearchEvent.OnSearchFood)
            },
            onQueryChange = {
                onEvent(SearchEvent.OnQueryChange(query = it))
            },
            onFocusChanged = {
                onEvent(SearchEvent.OnSearchFocusChange(isFocused = it.isFocused))
            },
            showHint = state.isHintVisible
        )
        Spacer(modifier = Modifier.size(LocalSpacing.current.md))
        LazyColumn {
            items(
                items = state.foods
            ) { state ->
                TrackableFoodCard(
                    modifier = Modifier.height(100.dp),
                    state = state,
                    onExpandClick = {
                        onEvent(SearchEvent.OnExpandFood(food = state.food))
                    },
                    onTrackFoodClick = {
                        keyboardController?.hide()
                        onEvent(
                            SearchEvent.OnTrackFoodClick(
                                food = state.food,
                                mealType = MealType.fromString(mealName),
                                date = LocalDate.of(year, month, dayOfMonth)
                            )
                        )
                    },
                    onAmountFoodChange = { amount ->
                        onEvent(SearchEvent.OnAmountFoodChange(food = state.food, amount = amount))
                    }
                )
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        when {
            state.isSearching -> CircularProgressIndicator()
            state.foods.isEmpty() -> {
                Text(
                    text = stringResource(id = R.string.no_results),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        keyboardController = null,
        state = SearchState.default(),
        onEvent = { },
        mealName = "breakfast",
        dayOfMonth = 633,
        month = 8228,
        year = 1799
    )
}