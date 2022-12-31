package com.mzarubin.taskscheduler.ui.authorization

import androidx.fragment.app.DialogFragment
import com.mzarubin.taskscheduler.util.DIALOG_HEIGHT_RATIO
import com.mzarubin.taskscheduler.util.DIALOG_WIDTH_RATIO

class WelcomeFragment: DialogFragment() {
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * DIALOG_WIDTH_RATIO).toInt(),
            (resources.displayMetrics.heightPixels * DIALOG_HEIGHT_RATIO).toInt()
        )
    }
}