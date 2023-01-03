package com.mzarubin.taskscheduler.datamodel

import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

data class ExternalNavigationDataModel(
    val cls: KClass<out AppCompatActivity>,
    val flags: Int? = null,
    val action: String? = null
)