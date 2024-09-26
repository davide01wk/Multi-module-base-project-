package com.deme.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deme.core.presentation.R
import com.deme.presentation.theme.CaloryTrackerTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DaySelector(
    modifier: Modifier = Modifier,
    onPreviousDayClick: () -> Unit,
    onNextDayClick: () -> Unit,
    localdate: LocalDate
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousDayClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.previous_day)
            )
        }
        Text(
            text = parseDateText(date = localdate)
        )
        IconButton(onClick = onNextDayClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = stringResource(id = R.string.next_day)
            )
        }
    }
}

@Composable
fun parseDateText(date: LocalDate): String {
    val today = LocalDate.now()
    return when(date){
        today.minusDays(1) -> stringResource(R.string.yesterday)
        today.plusDays(1) -> stringResource(R.string.tomorrow)
        today -> stringResource(R.string.today)
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(date)
    }
}

@Composable
@Preview
fun DaySelectorPreview(){
    CaloryTrackerTheme {
        DaySelector(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            onNextDayClick = { },
            onPreviousDayClick = { },
            localdate = LocalDate.now()
        )
    }
}
