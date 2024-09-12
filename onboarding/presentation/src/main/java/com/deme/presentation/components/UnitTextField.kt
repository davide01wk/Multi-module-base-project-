package com.deme.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalFontSize
import com.deme.presentation.theme.LocalSpacing

@Composable
fun UnitTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    unit: String,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.primaryVariant,
        fontSize = LocalFontSize.current.fontSize70
    )
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            modifier = Modifier
                .width(IntrinsicSize.Min) // wrap content
                .alignBy(LastBaseline)

        )
        Spacer(modifier = Modifier.size(LocalSpacing.current.xs))
        Text(
            text = unit,
            modifier = Modifier.alignBy(LastBaseline)
        )
    }
}

@Composable
@Preview
fun UnitTextFieldPreview(){
    CaloryTrackerTheme {
        UnitTextField(
            value = "180",
            unit = "cm",
            onValueChange = {}
        )
    }
}