package com.deme.presentation.search.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.deme.core.presentation.R
import com.deme.domain.model.TrackableFood
import com.deme.presentation.component.NutrientInfo
import com.deme.presentation.search.SearchState
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalSpacing

@OptIn(ExperimentalCoilApi::class)
@Composable
fun TrackableFoodCard(
    modifier: Modifier = Modifier,
    state: SearchState.TrackableFoodUiState,
    onExpandClick: () -> Unit,
    onAmountFoodChange: (String) -> Unit,
    onTrackFoodClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .clickable {
                onExpandClick()
            }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(end = LocalSpacing.current.sm),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = state.food.imageUrl,
                    builder = {
                        crossfade(true)
                        error(R.drawable.ic_burger)
                        fallback(R.drawable.ic_burger)
                    }
                ),
                contentDescription = state.food.name,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(
                        RoundedCornerShape(
                            topStart = 5.dp
                        )
                    )
            )
            Spacer(modifier = Modifier.size(LocalSpacing.current.md))
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = state.food.name,
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    stringResource(
                        id = R.string.kcal_per_100g,
                        state.food.caloriesPer100g
                    ),
                    style = MaterialTheme.typography.body2
                )
            }
            Spacer(modifier = Modifier.size(LocalSpacing.current.md))
            Row {
                NutrientInfo(
                    name = stringResource(R.string.carbs),
                    value = state.food.carbsPer100g.toString(),
                    unit = stringResource(R.string.grams)
                )
                Spacer(modifier = Modifier.size(LocalSpacing.current.sm))
                NutrientInfo(
                    name = stringResource(R.string.protein),
                    value = state.food.proteinPer100g.toString(),
                    unit = stringResource(R.string.grams)
                )
                Spacer(modifier = Modifier.size(LocalSpacing.current.sm))
                NutrientInfo(
                    name = stringResource(R.string.fat),
                    value = state.food.fatPer100g.toString(),
                    unit = stringResource(R.string.grams)
                )
            }
        }
        AnimatedVisibility(state.isExpanded) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = LocalSpacing.current.sm),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    BasicTextField(
                        value = state.amount,
                        onValueChange = onAmountFoodChange,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .alignBy(LastBaseline)
                            .border(
                                shape = RoundedCornerShape(5.dp),
                                width = 0.5.dp,
                                color = MaterialTheme.colors.onSurface
                            )
                            .semantics {
                                contentDescription = "amount_text_field"
                            }
                            .alignBy(LastBaseline)
                            .padding(LocalSpacing.current.md)

                    )
                    Text(
                        modifier = Modifier
                            .alignBy(LastBaseline)
                            .padding(LocalSpacing.current.sm),
                        text = stringResource(R.string.grams)
                    )
                }

                IconButton(
                    onClick = onTrackFoodClick,
                    enabled = state.amount.isNotBlank()
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add)
                    )
                }

            }
        }
    }

}

@Preview
@Composable
fun TrackableFoodCardPreview() {
    CaloryTrackerTheme {
        TrackableFoodCard(
            modifier = Modifier.height(100.dp),
            state = SearchState.TrackableFoodUiState(
                food = TrackableFood(
                    name = "Pizza",
                    imageUrl = null,
                    caloriesPer100g = 600,
                    carbsPer100g = 73,
                    proteinPer100g = 50,
                    fatPer100g = 12
                ), isExpanded = true, amount = "100"
            ),
            onExpandClick = { },
            onAmountFoodChange = { },
            onTrackFoodClick = { }
        )
    }
}