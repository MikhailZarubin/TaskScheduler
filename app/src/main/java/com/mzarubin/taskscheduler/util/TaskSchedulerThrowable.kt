package com.mzarubin.taskscheduler.util

import com.mzarubin.taskscheduler.datamodel.InternalError

class TaskSchedulerThrowable(val error: InternalError, val errorMessage: String? = null) :
    Throwable(error.description)