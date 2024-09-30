package com.deme.presentation.tracker_overview.components

import androidx.compose.foundation.Image
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
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.deme.core.presentation.R
import com.deme.domain.model.MealType
import com.deme.domain.model.TrackedFood
import com.deme.presentation.component.NutrientInfo
import com.deme.presentation.theme.LocalSpacing
import java.time.LocalDate

@OptIn(ExperimentalCoilApi::class)
@Composable
fun TrackedFoodCard(
    modifier: Modifier = Modifier,
    food: TrackedFood,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .padding(end = LocalSpacing.current.sm)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LocalSpacing.current.md)
    ) {
        Image(
            painter = rememberImagePainter(
                data = food.imageUrl,
                builder = {
                    crossfade(true)
                    error(R.drawable.ic_burger)
                    fallback(R.drawable.ic_burger)
                }
            ),
            contentDescription = food.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(
                    RoundedCornerShape(
                        topStart = 5.dp,
                        bottomStart = 5.dp
                    )
                )
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(vertical = LocalSpacing.current.sm)
        ) {
            Text(
                text = food.name,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Text(
                stringResource(
                    id = R.string.nutrient_info,
                    food.amount,
                    food.calories
                ),
                style = MaterialTheme.typography.body2
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = LocalSpacing.current.sm),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier.clickable {
                    onDeleteClick()
                },
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.delete)
            )
            Row {
                NutrientInfo(
                    name = stringResource(R.string.carbs),
                    value = food.carbs.toString(),
                    unit = stringResource(R.string.grams)
                )
                Spacer(modifier = Modifier.size(LocalSpacing.current.xs))
                NutrientInfo(
                    name = stringResource(R.string.protein),
                    value = food.protein.toString(),
                    unit = stringResource(R.string.grams)
                )
                Spacer(modifier = Modifier.size(LocalSpacing.current.xs))
                NutrientInfo(
                    name = stringResource(R.string.fat),
                    value = food.fat.toString(),
                    unit = stringResource(R.string.grams)
                )
            }
        }
    }
}

@Preview
@Composable
private fun TrackedFoodCardPreview() {
    TrackedFoodCard(modifier = Modifier
        .height(100.dp), food = TrackedFood(
        name = "Nakesha",
        carbs = 23,
        protein = 23,
        fat = 23,
        imageUrl = null,
        mealType = MealType.Dinner,
        amount = 23,
        date = LocalDate.now(),
        calories = 42,
        id = null
    ), onDeleteClick = {  }

    )
}
