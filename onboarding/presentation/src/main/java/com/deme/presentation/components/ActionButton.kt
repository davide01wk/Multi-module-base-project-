package com.deme.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.deme.presentation.LocalSpacing

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.button
){
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnabled,
        shape = RoundedCornerShape(LocalSpacing.current.xxl)
    ) {
        Text(
            modifier = Modifier.padding(LocalSpacing.current.sm),
            text = text,
            style = textStyle,
            color = MaterialTheme.colors.onPrimary
        )
    }
}
