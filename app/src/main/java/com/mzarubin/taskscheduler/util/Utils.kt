package com.mzarubin.taskscheduler.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun pendingDisplayLoading(scope: CoroutineScope, loadingCallback: (Boolean) -> Unit): Job {
    return scope.launch {
        delay(INSTANT_LOAD_TIMEOUT_MS)
        loadingCallback(true)
        delay(MINIMUM_LOADING_TIMEOUT_MS)
        loadingCallback(false)
    }
}