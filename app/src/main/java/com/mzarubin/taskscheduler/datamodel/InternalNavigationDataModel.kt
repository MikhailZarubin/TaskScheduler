package com.mzarubin.taskscheduler.datamodel

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

data class InternalNavigationDataModel(
    val navDirection: NavDirections,
    val navOptions: NavOptions? = null
)