package com.norm.mynavigatefromviewmodels

import androidx.navigation.NavOptionsBuilder

sealed interface NavigationAction {
    data class Navigate(
        val destination: Destination,
        val navOptions: NavOptionsBuilder.() -> Unit,
    ) : NavigationAction

    data object NavigationUp : NavigationAction
}