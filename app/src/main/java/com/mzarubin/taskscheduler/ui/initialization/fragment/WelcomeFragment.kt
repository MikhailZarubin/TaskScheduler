package com.mzarubin.taskscheduler.ui.initialization.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mzarubin.taskscheduler.databinding.FragmentWelcomeBinding
import com.mzarubin.taskscheduler.util.WELCOME_DIALOG_HEIGHT_RATIO
import com.mzarubin.taskscheduler.util.WELCOME_DIALOG_WIDTH_RATIO

class WelcomeFragment: DialogFragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding get() = _binding!!

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * WELCOME_DIALOG_WIDTH_RATIO).toInt(),
            (resources.displayMetrics.heightPixels * WELCOME_DIALOG_HEIGHT_RATIO).toInt()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.closeWelcomeButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}