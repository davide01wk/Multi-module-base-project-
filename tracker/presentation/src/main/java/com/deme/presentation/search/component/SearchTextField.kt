package com.deme.presentation.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deme.core.presentation.R
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalSpacing

@Composable
fun SearchTextField(
    text: String,
    modifier: Modifier = Modifier,
    onSearch: () -> Unit,
    onQueryChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    hint: String = stringResource(id = R.string.search),
    showHint: Boolean = false,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colors.surface)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(5.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            BasicTextField(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = "search_text_field"
                    }
                    .padding(start = LocalSpacing.current.sm)
                    .onFocusChanged { onFocusChanged(it) },
                value = text,
                onValueChange = {
                    onQueryChange(it)
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch()
                        defaultKeyboardAction(ImeAction.Search)
                    }
                ),
                singleLine = true
            )
            if (showHint) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = LocalSpacing.current.sm),
                    text = hint,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Light,
                    color = Color.LightGray
                )
            }
        }

        Icon(
            modifier = Modifier
                .padding(LocalSpacing.current.sm)
                .clickable {
                    onSearch()
                },
            imageVector = Icons.Default.Search,
            contentDescription = stringResource(R.string.search)
        )
    }

}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    CaloryTrackerTheme {
        SearchTextField(
            text = "",
            modifier = Modifier.fillMaxWidth(),
            onSearch = {},
            onQueryChange = {},
            onFocusChanged = {}
        )
    }
}