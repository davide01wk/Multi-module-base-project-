package com.deme.calorytracker.navigation

import androidx.navigation.NavController
import com.deme.presentation.navigation.UiEvent


fun NavController.navigate(event: UiEvent.Navigate){
    this.navigate(route = event.route)
}
