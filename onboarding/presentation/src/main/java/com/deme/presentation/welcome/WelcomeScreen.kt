package com.deme.presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.deme.core.presentation.R
import com.deme.presentation.LocalSpacing
import com.deme.presentation.components.ActionButton

@Composable
fun WelcomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_text)
        )
        Spacer(modifier = Modifier.size(LocalSpacing.current.md))
        ActionButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            isEnabled = true,
            text = stringResource(id = R.string.next),
            onClick = { /*TODO*/ }
        )
    }
}


